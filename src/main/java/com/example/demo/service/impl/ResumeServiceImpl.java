package com.example.demo.service.impl;

import com.example.demo.dao.ResumeDao;
import com.example.demo.dto.*;
import com.example.demo.model.RespondedApplicant;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import com.example.demo.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static com.example.demo.enums.AccountType.APPLICANT;
import static com.example.demo.enums.AccountType.EMPLOYER;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeDao resumeDao;
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final RespondedApplicantsRepository respondedApplicantsRepository;
    private final CategoryRepository categoryRepository;
    private final WorkExperienceInfoService workExperienceInfoService;
    private final EducationInfoService educationInfoService;
    private final ContactInfoService contactInfoService;
    private final VacancyService vacancyService;
    private final UserUtil userUtil;

    private final DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");

    @Override
    public Page<ResumeDto> getAllResumes(Authentication authentication, Pageable pageable) {
        String authority = userUtil.getAuthority(authentication);

        if (authority.equals(EMPLOYER.toString())) {
           Page<Resume> resumesPageable = resumeRepository.findAll(pageable);
           List<Resume> resumes = resumesPageable.getContent();
           return new PageImpl<>(transformationForListDtoResume(resumes));
        }

        throw new IllegalArgumentException("Your role is not appropriate");
    }

    @Override
    public ResumeDto getResumesByCategoryId(Long id, Authentication auth) {
        String authority = userUtil.getAuthority(auth);

        if (authority.equals(EMPLOYER.toString())) {
            Resume resume = resumeRepository.findByCategoryId(id).orElseThrow(() -> new NoSuchElementException("Resume is not found"));

            return transformationForSingleDtoResume(resume);
        }

        throw new IllegalArgumentException("Your role is not appropriate");
    }

    @Override
    public List<ResumeDto> getResumesByApplicantId(Authentication auth) {
        User user = userUtil.getUserByAuth(auth);

        if (user.getAccountType().equals(String.valueOf(APPLICANT))) {
            List<Resume> resumes = resumeRepository.findAllByApplicantId(user.getId());
            if (resumes != null && !resumes.isEmpty()) {
                return transformationForListDtoResume(resumes);
            } else {
                return null;
            }
        }

        return null;
    }


    @Override
    public ResumeDto getResumeById(Long id, Authentication auth) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can not find Resume by ID:" + id));

        return transformationForSingleDtoResume(resume);

    }

    @Override
    public boolean deleteResumeById(Long id, Authentication auth) {
        User user = userUtil.getUserByAuth(auth);

        if (!user.getAccountType().equals(String.valueOf(APPLICANT))) {
            return false;
        }

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can not find Resume by ID:" + id));

        if (Objects.equals(resume.getApplicant().getId(), user.getId())) {
            resumeRepository.deleteById(id);
            contactInfoService.delete(id);
            workExperienceInfoService.delete(id);
            educationInfoService.delete(id);
            return true;
        }

        throw new IllegalArgumentException("Your role is not appropriate");
    }

    @Override
    public void addResume(ResumeCreateDto resumeDto, Authentication auth) {
        String authority = userUtil.getAuthority(auth);
        User user = userUtil.getUserByAuth(auth);

        if (authority.equals(APPLICANT.toString())) {
            Resume resume = Resume.builder()
                    .name(resumeDto.getTitle())
                    .salary(resumeDto.getSalary())
                    .isActive(resumeDto.getIsActive())
                    .createdDate(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .applicant(userRepository.findByEmail(user.getEmail()).get())
                    .category(categoryRepository.findByName(resumeDto.getCategoryName()).get())

                    .build();

            Resume newResume = resumeRepository.save(resume);

            resumeDto.getWorkExperienceInfo()
                    .forEach(wei -> workExperienceInfoService.createWorkExperienceInfo(newResume.getId(), wei));
            resumeDto.getEducationInfo()
                    .forEach(ei -> educationInfoService.createEducationInfo(newResume.getId(), ei));

            for (var contact : resumeDto.getContacts()) {
                if (!contact.getValue().equals("")) contactInfoService.createContactInfo(newResume.getId(), contact);
            }
        }
    }

    @Override
    public void editResume(ResumeUpdateDto resumeDto, long id, Authentication auth) {
        String authority = userUtil.getAuthority(auth);
        User user = userUtil.getUserByAuth(auth);
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resume is not found"));

        if (authority.equalsIgnoreCase(APPLICANT.toString()) && Objects.equals(resume.getApplicant().getId(), user.getId())) {

            resume.setName(resumeDto.getTitle());
            resume.setSalary(resumeDto.getSalary());
            resume.setIsActive(resumeDto.getIsActive());
            resume.setCategory(categoryRepository.findByName(resumeDto.getCategoryName()).get());

            resumeRepository.save(resume);
        }
    }

    @Override
    public void updateResume(Long id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resume not found"));

        resume.setUpdateTime(LocalDateTime.now());
        resumeRepository.save(resume);
    }

    @Override
    public List<ResumeResponseDto> getResponsesResumes(Long userId, Authentication authentication) {
        List<RespondedApplicant> responses = respondedApplicantsRepository.findAllByResumeApplicantId(userId);
        List<ResumeResponseDto> resumes = new ArrayList<>();
        List<Long> employersId = new ArrayList<>();

        responses.forEach(response ->
                employersId.add(
                        vacancyService.getAuthorIdByVacancy(response.getVacancy().getId()))
        );


        for (int i = 0; i < responses.size(); i++) {
            RespondedApplicant response = responses.get(i);
            Long employerId = employersId.get(i);

            ResumeDto resume = getResumeById(response.getResume().getId(), authentication);

            ResumeResponseDto resumeResponse = ResumeResponseDto.builder()
                    .employerId(employerId)
                    .applicant(resume.getApplicant())
                    .updateTime(resume.getUpdateTime())
                    .createdDate(resume.getCreatedDate())
                    .categoryId(resume.getCategoryId())
                    .id(resume.getId())
                    .isActive(resume.getIsActive())
                    .name(resume.getName())
                    .salary(resume.getSalary())
                    .build();

            resumes.add(resumeResponse);
        }

        return resumes;
    }

    private ResumeDto transformationForSingleDtoResume(Resume resume) {
        return ResumeDto.builder()
                .id(resume.getId())
                .name(resume.getName())
                .salary(resume.getSalary())
                .isActive(resume.getIsActive())
                .categoryId(resume.getCategory().getId())
                .applicant(resume.getApplicant().getId())
                .createdDate(resume.getCreatedDate().format(formatters))
                .updateTime(resume.getUpdateTime().format(formatters))
                .build();
    }

    private List<ResumeDto> transformationForListDtoResume(List<Resume> resumes) {
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> {
            dtos.add(ResumeDto.builder()
                    .id(e.getId())
                    .name(e.getName())
                    .salary(e.getSalary())
                    .isActive(e.getIsActive())
                    .categoryId(e.getCategory().getId())
                    .applicant(e.getApplicant().getId())
                    .createdDate(e.getCreatedDate().format(formatters))
                    .updateTime(e.getUpdateTime().format(formatters))
                    .build());
        });

        return dtos;
    }

}
