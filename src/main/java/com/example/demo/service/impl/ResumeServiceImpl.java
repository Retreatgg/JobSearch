package com.example.demo.service.impl;

import com.example.demo.dao.ResumeDao;
import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;

    @Override
    public ResumeDto getResumesByCategoryId(Long id) {
        Resume resume = resumeDao.getResumesByCategoryId(id);
        return transformationForSingleDtoResume(resume);
    }

    @Override
    public List<ResumeDto> getResumesByName(String name) {
        List<Resume> resumes = resumeDao.getResumesByApplicant(name);
        return transformationForListDtoResume(resumes);
    }

    @SneakyThrows
    @Override
    public ResumeDto getResumeById(Long id) {
        Resume resume = resumeDao.getResumeById(id).orElseThrow(() -> new Exception("Can not find Resume by ID:" + id));;
        return transformationForSingleDtoResume(resume);
    }

    @Override
    public List<ResumeDto> getResumesByApplicantId(Long id) {
        List<Resume> resumes = resumeDao.getResumesByApplicantId(id);
        return transformationForListDtoResume(resumes);
    }

    @Override
    public boolean deleteResumeById(Long id) {
        if(resumeDao.getResumeById(id).isPresent()) {
            resumeDao.deleteResumeById(id);
            return true;
        }
        return false;
    }

    @Override
    public void addResume(ResumeDto resumeDto) {
        Resume resume = new Resume();
        resume = editAndAdd(resume, resumeDto);
        resumeDao.addResume(resume);
    }

    @Override
    public void editResume(ResumeDto resumeDto, long id) {
        Resume resume = new Resume();
        resume = editAndAdd(resume, resumeDto);
        resumeDao.editResume(resume, id);
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

    private Resume editAndAdd(Resume resume, ResumeDto resumeDto) {
        resume.setId(resumeDto.getId());
        resume.setName(resumeDto.getName());
        resume.setSalary(resumeDto.getSalary());
        resume.setIsActive(resumeDto.getIsActive());
        resume.setCreatedDate(resumeDto.getCreatedDate());
        resume.setUpdateTime(resumeDto.getUpdateTime());
        resume.setApplicantId(resumeDto.getApplicant());
        resume.setCategoryId(resumeDto.getCategoryId());

        return resume;
    }

}
