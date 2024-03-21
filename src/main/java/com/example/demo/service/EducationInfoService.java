package com.example.demo.service;

import com.example.demo.dto.EducationInfoDto;
import org.springframework.stereotype.Service;

@Service
public interface EducationInfoService {
    void createEducationInfo(Long id, EducationInfoDto educationInfoDto);
}
