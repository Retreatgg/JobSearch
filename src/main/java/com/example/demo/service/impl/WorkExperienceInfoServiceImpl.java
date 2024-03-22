package com.example.demo.service.impl;

import com.example.demo.dao.WorkExperienceInfoDao;
import com.example.demo.dto.WorkExperienceInfoDto;
import com.example.demo.model.WorkExperienceInfo;
import com.example.demo.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
