package com.example.demo.service;

import com.example.demo.dto.ResumeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResumeService {
    ResumeDto getResumesByCategory(Long id);
    List<ResumeDto> getResumesByName(String name);
}
