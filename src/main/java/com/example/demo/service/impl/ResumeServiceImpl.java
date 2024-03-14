package com.example.demo.service.impl;

import com.example.demo.dao.ResumeDao;
import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;

    @Override
    public ResumeDto getResumesByCategory(Long id) {
        Resume e = resumeDao.getResumesByCategory(id);
        return ResumeDto.builder()
                .id(e.getId())
                .name(e.getName())
                .salary(e.getSalary())
                .isActive(e.getIsActive())
                .categoryId(e.getCategoryId())
                .applicantId(e.getApplicantId())
                .createdDate(e.getCreatedDate())
                .updateTime(e.getUpdateTime())
                .build();

    }

    @Override
    public List<ResumeDto> getResumesByName(String name) {
        List<Resume> resumes = resumeDao.getResumesByApplicant(name);
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> {
            dtos.add(ResumeDto.builder()
                    .id(e.getId())
                    .name(e.getName())
                    .salary(e.getSalary())
                    .isActive(e.getIsActive())
                    .categoryId(e.getCategoryId())
                    .applicantId(e.getApplicantId())
                    .createdDate(e.getCreatedDate())
                    .updateTime(e.getUpdateTime())
                    .build());
        });

        return dtos;
    }

    @Override
    public ResumeDto getResumeById(Long id) {
        Resume e = resumeDao.getResumeById(id);
        return ResumeDto.builder()
                .id(e.getId())
                .name(e.getName())
                .salary(e.getSalary())
                .isActive(e.getIsActive())
                .categoryId(e.getCategoryId())
                .applicantId(e.getApplicantId())
                .createdDate(e.getCreatedDate())
                .updateTime(e.getUpdateTime())
                .build();
    }

    @Override
    public List<ResumeDto> getResumesByApplicantId(Long id) {
        List<Resume> resumes = resumeDao.getResumesByApplicantId(id);
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> {
            dtos.add(ResumeDto.builder()
                    .id(e.getId())
                    .name(e.getName())
                    .salary(e.getSalary())
                    .isActive(e.getIsActive())
                    .categoryId(e.getCategoryId())
                    .applicantId(e.getApplicantId())
                    .createdDate(e.getCreatedDate())
                    .updateTime(e.getUpdateTime())
                    .build());
        });

        return dtos;
    }

}
