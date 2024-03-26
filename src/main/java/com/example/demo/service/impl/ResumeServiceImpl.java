package com.example.demo.service.impl;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.ResumeDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.ResumeCreateDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.ResumeUpdateDto;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import com.example.demo.service.ContactInfoService;
import com.example.demo.service.EducationInfoService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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

    @Override
    public List<ResumeDto> getAllResumes(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        if(user.getAccountType().equals("Employer")) {
            List<Resume> resumes = resumeDao.getAllResumes();
            return transformationForListDtoResume(resumes);
        }

        return null;
    }

    @Override
    public ResumeDto getResumesByCategoryId(Long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        if (user != null && user.getAccountType().equals("Employer")) {
            Optional<Resume> resumeOptional = resumeDao.getResumesByCategoryId(id);
            if (resumeOptional.isPresent()) {
                Resume resume = resumeOptional.get();
                return transformationForSingleDtoResume(resume);
            }
        }
        return null;
    }

    @Override
    public List<ResumeDto> getResumesByApplicantId(long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        if (user != null && user.getAccountType().equals("Employer")) {
            List<Resume> resumes = resumeDao.getResumesByApplicant(id);
            if (resumes != null && !resumes.isEmpty()) {
                return transformationForListDtoResume(resumes);
            } else {
                throw new NoSuchElementException("No resumes found for applicant with ID: " + id);
            }
        }
        return null;
    }


    @Override
    public ResumeDto getResumeById(Long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        if (user != null && user.getAccountType().equals("Employer")) {
            Resume resume = resumeDao.getResumeById(id).orElseThrow(() -> new NoSuchElementException("Can not find Resume by ID:" + id));
            return transformationForSingleDtoResume(resume);
        }
        return null;
    }

    @Override
    public boolean deleteResumeById(Long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        if (user == null || !user.getAccountType().equals("Applicant")) {
            return false;
        }

        Optional<Resume> optionalResume = resumeDao.getResumeById(id);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            if (resume.getApplicantId() == user.getId()) {
                resumeDao.deleteResumeById(id);
                contactInfoService.delete(id);
                workExperienceInfoService.delete(id);
                educationInfoService.delete(id);
                return true;
            } else {
                throw new NoSuchElementException("User with ID " + user.getId() + " is not authorized to delete resume with ID " + id);
            }
        } else {
            throw new NoSuchElementException("Resume with ID " + id + " not found");
        }
    }

    @Override
    public void addResume(ResumeCreateDto resumeDto, Authentication auth) {
        User user = (User) auth.getPrincipal();
        if (user.getAccountType().equals("Applicant")) {
            Resume resume = new Resume();
            resume.setName(resumeDto.getTitle());
            resume.setSalary(resumeDto.getSalary());
            resume.setIsActive(resumeDto.getIsActive());
            resume.setCreatedDate(LocalDateTime.now());
            resume.setUpdateTime(LocalDateTime.now());
            resume.setApplicantId(userDao.returnIdByEmail(resumeDto.getAuthorEmail()));
            resume.setCategoryId(categoryDao.returnIdByName(resumeDto.getCategoryName()));
            resumeDao.addResume(resume);
            resumeDto.getWorkExperienceInfo().forEach(wei -> workExperienceInfoService.createWorkExperienceInfo(resume.getId(), wei));
            resumeDto.getEducationInfo().forEach(ei -> educationInfoService.createEducationInfo(resume.getId(), ei));
            contactInfoService.createContactInfo(resume.getId(), resumeDto.getContacts());
        }

    }

    @Override
    public void editResume(ResumeUpdateDto resumeDto, long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        Optional<Resume> resumeOptional = resumeDao.getResumeById(id);
        if (user != null && user.getAccountType().equals("Applicant")
                && resumeOptional.isPresent() && Objects.equals(resumeOptional.get().getApplicantId(), user.getId())) {
            Resume resume = new Resume();
            resume.setId(id);
            resume.setName(resumeDto.getTitle());
            resume.setSalary(resumeDto.getSalary());
            resume.setIsActive(resumeDto.getIsActive());
            resume.setCategoryId(categoryDao.returnIdByName(resumeDto.getCategoryName()));
            resumeDao.editResume(resume);
            resumeDto.getWorkExperienceInfo().forEach(wei -> workExperienceInfoService.createWorkExperienceInfo(resume.getId(), wei));
            resumeDto.getEducationInfo().forEach(ei -> educationInfoService.createEducationInfo(resume.getId(), ei));
            contactInfoService.createContactInfo(resume.getId(), resumeDto.getContacts());
        }
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
