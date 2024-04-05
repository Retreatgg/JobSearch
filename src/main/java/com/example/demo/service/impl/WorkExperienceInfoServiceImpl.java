package com.example.demo.service.impl;

import com.example.demo.dao.WorkExperienceInfoDao;
import com.example.demo.dto.WorkExperienceInfoDto;
import com.example.demo.model.WorkExperienceInfo;
import com.example.demo.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperienceInfoServiceImpl implements WorkExperienceInfoService {

    private final WorkExperienceInfoDao workExperienceInfoDao;
    @Override
    public void createWorkExperienceInfo(Long id, WorkExperienceInfoDto workExperienceInfoDto) {
        WorkExperienceInfo workExperienceInfo = new WorkExperienceInfo();
        workExperienceInfo.setResumeId(id);
        workExperienceInfo.setYears(workExperienceInfoDto.getYears());
        workExperienceInfo.setPosition(workExperienceInfoDto.getPosition());
        workExperienceInfo.setCompanyName(workExperienceInfoDto.getCompanyName());
        workExperienceInfo.setResponsibilities(workExperienceInfoDto.getResponsibilities());
        workExperienceInfoDao.createWorkExperienceInfo(workExperienceInfo);
    }

    @Override
    public void delete(long id) {
        workExperienceInfoDao.delete(id);
    }

    @Override
    public List<WorkExperienceInfoDto> getWorkInfo(Long resumeId) {
        List<WorkExperienceInfo> workExperienceInfo = workExperienceInfoDao.getWorkInfo(resumeId);
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
