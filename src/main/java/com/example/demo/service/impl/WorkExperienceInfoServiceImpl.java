package com.example.demo.service.impl;

import com.example.demo.dto.WorkExperienceInfoDto;
import com.example.demo.model.WorkExperienceInfo;
import com.example.demo.repository.ResumeRepository;
import com.example.demo.repository.WorkExperienceRepository;
import com.example.demo.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperienceInfoServiceImpl implements WorkExperienceInfoService {

    private final WorkExperienceRepository workExperienceRepository;
    private final ResumeRepository resumeRepository;

    @Override
    public void createWorkExperienceInfo(Long id, WorkExperienceInfoDto workExperienceInfoDto) {
        WorkExperienceInfo workExperienceInfo = WorkExperienceInfo.builder()
                .years(workExperienceInfoDto.getYears())
                .companyName(workExperienceInfoDto.getCompanyName())
                .position(workExperienceInfoDto.getPosition())
                .responsibilities(workExperienceInfoDto.getResponsibilities())
                .resume(resumeRepository.findById(id).get())
                .build();

        workExperienceRepository.save(workExperienceInfo);
    }

    @Override
    public void delete(long id) {
        workExperienceRepository.deleteByResumeId(id);
    }

    @Override
    public List<WorkExperienceInfoDto> getWorkInfo(Long resumeId) {
        List<WorkExperienceInfo> workExperienceInfo = workExperienceRepository.findAllByResumeId(resumeId);
        List<WorkExperienceInfoDto> dtos = new ArrayList<>();
        workExperienceInfo.forEach(e -> {
            dtos.add(WorkExperienceInfoDto.builder()
                            .companyName(e.getCompanyName())
                            .years(e.getYears())
                            .position(e.getPosition())
                            .responsibilities(e.getResponsibilities())
                    .build());
        });
        return dtos;
    }
}
