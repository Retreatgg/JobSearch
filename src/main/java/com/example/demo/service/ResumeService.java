package com.example.demo.service;

import com.example.demo.dto.ResumeCreateDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.ResumeResponseDto;
import com.example.demo.dto.ResumeUpdateDto;
import com.example.demo.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResumeService {
    Page<ResumeDto> getAllResumes(Authentication authentication, Pageable pageable);
    ResumeDto getResumesByCategoryId(Long id, Authentication auth);
    List<ResumeDto> getResumesByApplicantId(Authentication auth);
    ResumeDto getResumeById(Long id, Authentication auth);
    ResponseEntity<ResumeDto> addResume(ResumeCreateDto resumeDto);
    ResponseEntity<ResumeDto> editResume(ResumeUpdateDto resumeDto, long id);
    ResponseEntity<ResumeDto> updateResume(Long id);
    List<ResumeResponseDto> getResponsesResumes(Long userId, Authentication authentication);
    Resume findById(Long resumeId);
    Long getAuthorIdByResume(Long id);
}
