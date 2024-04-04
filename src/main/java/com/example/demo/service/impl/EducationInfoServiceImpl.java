package com.example.demo.service.impl;

import com.example.demo.dao.EducationInfoDao;
import com.example.demo.dto.EducationInfoDto;
import com.example.demo.model.EducationInfo;
import com.example.demo.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceImpl implements EducationInfoService {

    private final EducationInfoDao educationInfoDao;

    @Override
    public void createEducationInfo(Long id, EducationInfoDto educationInfoDto) {
        EducationInfo educationInfo = new EducationInfo();
        educationInfo.setResumeId(id);
        educationInfo.setDegree(educationInfoDto.getDegree());
        educationInfo.setProgram(educationInfoDto.getProgram());
        educationInfo.setInstitution(educationInfoDto.getInstitution());
        educationInfo.setStartDate(educationInfoDto.getStartDate());
        educationInfo.setEndDate(educationInfoDto.getEndDate());

        educationInfoDao.createEducationInfo(educationInfo);
    }

    @Override
    public void delete(long id) {
        educationInfoDao.delete(id);
    }
}
