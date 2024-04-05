package com.example.demo.service;

import com.example.demo.dto.WorkExperienceInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkExperienceInfoService {
    void createWorkExperienceInfo(Long id, WorkExperienceInfoDto workExperienceInfoDto);
    void delete(long id);
    List<WorkExperienceInfoDto> getWorkInfo(Long resumeId);
}
