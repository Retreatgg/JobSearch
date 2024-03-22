package com.example.demo.service;

import com.example.demo.dto.WorkExperienceInfoDto;
import org.springframework.stereotype.Service;

@Service
public interface WorkExperienceInfoService {
    void createWorkExperienceInfo(Long id, WorkExperienceInfoDto workExperienceInfoDto);
    void delete(long id);
}
