package com.example.demo.service.impl;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.ResumeDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.*;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import com.example.demo.service.*;
import com.example.demo.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final UserDao userDao;
    private final CategoryDao categoryDao;
    private final WorkExperienceInfoService workExperienceInfoService;
    private final EducationInfoService educationInfoService;
    private final ContactInfoService contactInfoService;
    private final RespondedApplicantService respondedApplicantService;

    private final VacancyService vacancyService;
    private final FileUtil fileUtil;

    @Override
    public List<ResumeDto> getAllResumes(Authentication authentication, String page, String perPage) {
        int numberPage = Integer.parseInt(page);
        int sizePageNumber = Integer.parseInt(perPage);
        int offset = numberPage * sizePageNumber;

        String authority = fileUtil.getAuthority(authentication);

        if (authority.equals(EMPLOYER.toString())) {
            List<Resume> resumes = resumeDao.getAllResumes(sizePageNumber, offset);
            return transformationForListDtoResume(resumes);
        }

        throw new IllegalArgumentException("Your role is not appropriate");
    }

    @Override
    public ResumeDto getResumesByCategoryId(Long id, Authentication auth) {
        String authority = fileUtil.getAuthority(auth);

        if (authority.equals(EMPLOYER.toString())) {
            Resume resume = resumeDao.getResumesByCategoryId(id)
                    .orElseThrow(() -> new NoSuchElementException("Resume is not found"));

            return transformationForSingleDtoResume(resume);
        }

        throw new IllegalArgumentException("Your role is not appropriate");
    }

    @Override
    public List<ResumeDto> getResumesByApplicantId(Authentication auth) {
        User user = fileUtil.getUserByAuth(auth);

        if (user.getAccountType().equals(String.valueOf(APPLICANT))) {
            List<Resume> resumes = resumeDao.getResumesByApplicant(user.getId());
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
        Resume resume = resumeDao.getResumeById(id)
                .orElseThrow(() -> new NoSuchElementException("Can not find Resume by ID:" + id));

        return transformationForSingleDtoResume(resume);

    }

    @Override
    public boolean deleteResumeById(Long id, Authentication auth) {
        User user = fileUtil.getUserByAuth(auth);

        if (!user.getAccountType().equals(String.valueOf(APPLICANT))) {
            return false;
        }

        Resume resume = resumeDao.getResumeById(id)
                .orElseThrow(() -> new NoSuchElementException("Can not find Resume by ID:" + id));

        if (Objects.equals(resume.getApplicantId(), user.getId())) {
            resumeDao.deleteResumeById(id);
            contactInfoService.delete(id);
            workExperienceInfoService.delete(id);
            educationInfoService.delete(id);

            return true;
        }

        throw new IllegalArgumentException("Your role is not appropriate");
    }

    @Override
    public void addResume(ResumeCreateDto resumeDto, Authentication auth) {
        String authority = fileUtil.getAuthority(auth);
        User user = fileUtil.getUserByAuth(auth);

        if (authority.equals(APPLICANT.toString())) {
            Resume resume = new Resume();

            resume.setName(resumeDto.getTitle());
            resume.setSalary(resumeDto.getSalary());
            resume.setIsActive(resumeDto.getIsActive());
            resume.setCreatedDate(LocalDateTime.now());
            resume.setUpdateTime(LocalDateTime.now());
            resume.setApplicantId(userDao.returnIdByEmail(user.getEmail()));
            resume.setCategoryId(categoryDao.returnIdByName(resumeDto.getCategoryName()));

            Long resumeId = resumeDao.addResume(resume);

            resumeDto.getWorkExperienceInfo()
                    .forEach(wei -> workExperienceInfoService.createWorkExperienceInfo(resumeId, wei));
            resumeDto.getEducationInfo()
                    .forEach(ei -> educationInfoService.createEducationInfo(resumeId, ei));
            resumeDto.getContacts()
                    .forEach(c -> contactInfoService.createContactInfo(resumeId, c));
        }
    }

    @Override
    public void editResume(ResumeUpdateDto resumeDto, long id, Authentication auth) {
        String authority = fileUtil.getAuthority(auth);
        User user = fileUtil.getUserByAuth(auth);
        Resume resume = resumeDao.getResumeById(id)
                .orElseThrow(() -> new NoSuchElementException("Resume is not found"));

        if (authority.equalsIgnoreCase(APPLICANT.toString()) && Objects.equals(resume.getApplicantId(), user.getId())) {

            resume.setName(resumeDto.getTitle());
            resume.setSalary(resumeDto.getSalary());
            resume.setIsActive(resumeDto.getIsActive());
            resume.setCategoryId(categoryDao.returnIdByName(resumeDto.getCategoryName()));

            resumeDao.editResume(resume);

            resumeDto.getWorkExperienceInfo()
                    .forEach(wei -> workExperienceInfoService.createWorkExperienceInfo(resume.getId(), wei));
            resumeDto.getEducationInfo()
                    .forEach(ei -> educationInfoService.createEducationInfo(resume.getId(), ei));
            resumeDto.getContacts()
                    .forEach(ci -> contactInfoService.createContactInfo(resume.getId(), ci));
        }
    }

    @Override
    public void updateResume(Long id) {
        Resume resume = resumeDao.getResumeById(id)
                .orElseThrow(() -> new NoSuchElementException("Resume is not found"));

        resume.setUpdateTime(LocalDateTime.now());
        resumeDao.update(resume);
    }

    @Override
    public List<ResumeResponseDto> getResponsesResumes(Long userId, Authentication authentication) {
        List<RespondedApplicantsDto> responses = respondedApplicantService.getResponsesByApplicantId(userId);
        List<ResumeResponseDto> resumes = new ArrayList<>();
        List<Long> employersId = new ArrayList<>();

        responses.forEach(response ->
            employersId.add(
                    vacancyService.getAuthorIdByVacancy(response.getVacancyId()))
        );


        for (int i = 0; i < responses.size(); i++) {
            RespondedApplicantsDto response = responses.get(i);
            Long employerId = employersId.get(i);

            ResumeDto resume = getResumeById(response.getResumeId(), authentication);

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
                .categoryId(resume.getCategoryId())
                .applicant(resume.getApplicantId())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
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
                    .categoryId(e.getCategoryId())
                    .applicant(e.getApplicantId())
                    .createdDate(e.getCreatedDate())
                    .updateTime(e.getUpdateTime())
                    .build());
        });

        return dtos;
    }

}
