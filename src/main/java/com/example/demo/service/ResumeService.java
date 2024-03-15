package com.example.demo.service;

import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResumeService {
    ResumeDto getResumesByCategoryId(Long id);
    List<ResumeDto> getResumesByName(String name);

    ResumeDto getResumeById(Long id);
    List<ResumeDto> getResumesByApplicantId(Long id);
}
