package com.example.demo.service;

import com.example.demo.dto.ResumeCreateDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.ResumeUpdateDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResumeService {
    List<ResumeDto> getAllResumes(Authentication authentication);
    List<ResumeDto> getAllResumes();
    ResumeDto getResumesByCategoryId(Long id, Authentication auth);
    List<ResumeDto> getResumesByApplicantId(long id, Authentication auth);

    ResumeDto getResumeById(Long id, Authentication auth);

    boolean deleteResumeById(Long id, Authentication auth);
    void addResume(ResumeCreateDto resumeDto, Authentication auth);
    void editResume(ResumeUpdateDto resumeDto, long id, Authentication auth);
}
