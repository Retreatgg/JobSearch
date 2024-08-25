package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.mapper.ResumeMapper;
import com.example.demo.model.RespondedApplicant;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.RespondedApplicantsRepository;
import com.example.demo.repository.ResumeRepository;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.demo.enums.AccountType.APPLICANT;
import static com.example.demo.enums.AccountType.EMPLOYER;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final CategoryRepository categoryRepository;
    private final WorkExperienceInfoService workExperienceInfoService;
    private final EducationInfoService educationInfoService;
    private final ContactInfoService contactInfoService;
    private final ResumeMapper resumeMapper;
    private final UserService userService;

    @Override
    public Page<ResumeDto> getAllResumes(Pageable pageable) {
        Page<Resume> resumesPageable = resumeRepository.findAll(pageable);
        List<Resume> resumes = resumesPageable.getContent();
        return new PageImpl<>(resumeMapper.toListDto(resumes));
    }

    @Override
    public ResumeDto getResumesByCategoryId(Long id) {
        Resume resume = resumeRepository.findByCategoryId(id)
                .orElseThrow(() -> new NoSuchElementException("Resume is not found"));
        return resumeMapper.toDto(resume);
    }
//    @Override
//    public List<ResumeDto> getResumesByApplicantId(Authentication auth) {
//        User user = userUtil.getUserByAuth(auth);
//
//        if (user.getAccountType().equals(String.valueOf(APPLICANT))) {
//            List<Resume> resumes = resumeRepository.findAllByApplicantId(user.getId());
//            if (resumes != null && !resumes.isEmpty()) {
//                return resumeMapper.toListDto(resumes);
//            } else {
//                return null;
//            }
//        }
//
//        return null;
//    }

    @Override
    public ResumeDto getResumeById(Long id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can not find Resume by ID:" + id));

        return resumeMapper.toDto(resume);
    }

    @Override
    public ResponseEntity<ResumeDto> add(ResumeCreateDto resumeDto) {
        Resume resume = resumeMapper.toResume(resumeDto);
        resume.setApplicant(userService.getUserById(2L));
        Resume newResume = resumeRepository.save(resume);
        resumeDto.getWorkExperienceInfo()
                .forEach(wei -> workExperienceInfoService.createWorkExperienceInfo(newResume.getId(), wei));
        resumeDto.getEducationInfo()
                .forEach(ei -> educationInfoService.createEducationInfo(newResume.getId(), ei));

        for (ContactInfoDto contact : resumeDto.getContacts()) {
            if (!contact.getValue().equals("")) contactInfoService.createContactInfo(newResume.getId(), contact);
        }

        return ResponseEntity.ok(resumeMapper.toDto(newResume));
    }

    @Override
    public ResponseEntity<ResumeDto> edit(ResumeUpdateDto resumeDto, long id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resume is not found"));

        if (resumeDto.getTitle() != null) {
            resume.setName(resumeDto.getTitle());
        }
        if (resumeDto.getSalary() != null) {
            resume.setSalary(resumeDto.getSalary());
        }
        if (resumeDto.getIsActive() != null) {
            resume.setIsActive(resumeDto.getIsActive());
        }
        if (resumeDto.getCategoryName() != null) {
            resume.setCategory(categoryRepository.findByName(resumeDto.getCategoryName()).get());
        }

        Resume editedResume = resumeRepository.save(resume);
        return ResponseEntity.ok(resumeMapper.toDto(editedResume));
    }

    @Override
    public ResponseEntity<ResumeDto> update(Long id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resume not found"));

        resume.setUpdateTime(LocalDateTime.now());
        Resume updatedResume = resumeRepository.save(resume);
        return ResponseEntity.ok(resumeMapper.toDto(updatedResume));
    }

//    @Override
//    public List<ResumeResponseDto> getResponsesResumes(Long userId) {
//        List<RespondedApplicant> responses = respondedApplicantsRepository.findAllByResumeApplicantId(userId);
//        List<ResumeResponseDto> resumes = new ArrayList<>();
//        List<Long> employersId = new ArrayList<>();
//
//        responses.forEach(response ->
//                employersId.add(
//                        vacancyService.getAuthorIdByVacancy(response.getVacancy().getId()))
//        );
//
//
//        for (int i = 0; i < responses.size(); i++) {
//            RespondedApplicant response = responses.get(i);
//            Long employerId = employersId.get(i);
//            ResumeDto resume = getResumeById(response.getResume().getId());
//            ResumeResponseDto resumeResponse = resumeMapper.toResponseDto(resume);
//            resumeResponse.setEmployerId(employerId);
//            resumes.add(resumeResponse);
//        }
//
//        return resumes;
//    }

    @Override
    public Resume findById(Long resumeId) {
        return resumeRepository.findById(resumeId)
                .orElseThrow(() -> new NoSuchElementException("Resume is not found by ID" + resumeId));
    }

    @Override
    public void delete(Long id) {
        Resume resume = findById(id);
        if (resume != null) {
            resumeRepository.deleteById(id);
            contactInfoService.delete(id);
            workExperienceInfoService.delete(id);
            educationInfoService.delete(id);
        }
    }

}
