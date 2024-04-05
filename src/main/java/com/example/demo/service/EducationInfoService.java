package com.example.demo.service;

import com.example.demo.dto.EducationInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EducationInfoService {
    void createEducationInfo(Long id, EducationInfoDto educationInfoDto);
    void delete(long id);

    List<EducationInfoDto> getEducations(Long resumeId);
}

