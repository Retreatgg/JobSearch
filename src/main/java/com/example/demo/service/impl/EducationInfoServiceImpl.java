package com.example.demo.service.impl;

import com.example.demo.dao.EducationInfoDao;
import com.example.demo.dto.EducationInfoDto;
import com.example.demo.model.EducationInfo;
import com.example.demo.repository.EducationInfoRepository;
import com.example.demo.repository.ResumeRepository;
import com.example.demo.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceImpl implements EducationInfoService {

    private final EducationInfoDao educationInfoDao;
    private final ResumeRepository resumeRepository;
    private final EducationInfoRepository educationInfoRepository;

    @Override
    public void createEducationInfo(Long id, EducationInfoDto educationInfoDto) {
        EducationInfo educationInfo = EducationInfo.builder()
                .degree(educationInfoDto.getDegree())
                .endDate(educationInfoDto.getEndDate())
                .institution(educationInfoDto.getInstitution())
                .program(educationInfoDto.getProgram())
                .resumeId(resumeRepository.findById(id).get())
                .build();

        educationInfoRepository.save(educationInfo);
    }

    @Override
    public void delete(long id) {
        educationInfoRepository.deleteById(id);
    }

    @Override
    public List<EducationInfoDto> getEducations(Long resumeId) {
        List<EducationInfo> educationInfos = educationInfoRepository.findByResumeId(resumeId);
        List<EducationInfoDto> educationInfoDtos = new ArrayList<>();

        educationInfos.forEach(e -> {
            educationInfoDtos.add(EducationInfoDto.builder()
                            .degree(e.getDegree())
                            .endDate(e.getEndDate())
                            .institution(e.getInstitution())
                            .program(e.getProgram())
                            .startDate(e.getStartDate())
                    .build());
        });

        return educationInfoDtos;
    }
}
