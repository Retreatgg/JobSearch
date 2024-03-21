package com.example.demo.service.impl;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.ResumeDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.ResumeCreateDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;
    private final UserDao userDao;
    private final CategoryDao categoryDao;

    @Override
    public ResumeDto getResumesByCategoryId(Long id, long employerId) {
        User user = returnUserById(employerId);
        if (user != null && user.getAccountType().equals("Employer")) {
            Optional<Resume> resumeOptional = resumeDao.getResumesByCategoryId(id);
            if(resumeOptional.isPresent()) {
                Resume resume = resumeOptional.get();
                return transformationForSingleDtoResume(resume);
            }
        }
        return null;
    }

    @Override
    public List<ResumeDto> getResumesByApplicantId(long id, long employerId) {
        User user = returnUserById(employerId);
        if (user != null && user.getAccountType().equals("Employer")) {
            List<Resume> resumes = resumeDao.getResumesByApplicant(id);
            return transformationForListDtoResume(resumes);
        }
        return null;
    }


    @Override
    public ResumeDto getResumeById(Long id, long employerId) {
        User user = returnUserById(employerId);
        if (user != null && user.getAccountType().equals("Employer")) {
            try {
                Resume resume = resumeDao.getResumeById(id).orElseThrow(() -> new Exception("Can not find Resume by ID:" + id));
                return transformationForSingleDtoResume(resume);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public boolean deleteResumeById(Long id, long applicantId) {
        User user = returnUserById(applicantId);
        if (user == null || !user.getAccountType().equals("Applicant")) {
            return false;
        }

        Optional<Resume> optionalResume = resumeDao.getResumeById(id);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            if (resume.getApplicantId() == applicantId) {
                resumeDao.deleteResumeById(id);
                return true;
            }
        }

        return false;
    }

    @Override
    public void addResume(ResumeCreateDto resumeDto, long applicantId) {
        User user = returnUserById(applicantId);
        if (user != null && user.getAccountType().equals("Applicant")) {
            Resume resume = new Resume();
            resume.setName(resumeDto.getTitle());
            resume.setSalary(resumeDto.getSalary());
            resume.setIsActive(resumeDto.getIsActive());
            resume.setCreatedDate(LocalDateTime.now());
            resume.setUpdateTime(LocalDateTime.now());
            resume.setApplicantId(userDao.returnIdByEmail(resumeDto.getAuthorEmail()));
            resume.setCategoryId(categoryDao.returnIdByName(resumeDto.getCategoryName()));
            resumeDao.addResume(resume);
        }

    }

    @Override
    public void editResume(ResumeDto resumeDto, long applicantId) {
        User user = returnUserById(applicantId);
        if (user != null && user.getAccountType().equals("Applicant") && resumeDto.getApplicant() == applicantId) {
            Resume resume = new Resume();
            resume.setId(resumeDto.getId());
            resume.setName(resumeDto.getName());
            resume.setSalary(resumeDto.getSalary());
            resume.setIsActive(resumeDto.getIsActive());
            resume.setCreatedDate(resumeDto.getCreatedDate());
            resume.setUpdateTime(LocalDateTime.now());
            resume.setApplicantId(resumeDto.getApplicant());
            resume.setCategoryId(resumeDto.getCategoryId());
            resumeDao.editResume(resume);
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

    private User returnUserById(long id) {
        Optional<User> optionalUser = userDao.getById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user;
        }
        return null;
    }

}
