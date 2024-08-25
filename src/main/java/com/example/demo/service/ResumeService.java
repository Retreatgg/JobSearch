package com.example.demo.service;

import com.example.demo.dto.ResumeCreateDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.ResumeResponseDto;
import com.example.demo.dto.ResumeUpdateDto;
import com.example.demo.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResumeService {
    Page<ResumeDto> getAllResumes(Pageable pageable);
    ResumeDto getResumesByCategoryId(Long id);
    ResumeDto getResumeById(Long id);
    ResponseEntity<ResumeDto> add(ResumeCreateDto resumeDto);
    ResponseEntity<ResumeDto> edit(ResumeUpdateDto resumeDto, long id);
    ResponseEntity<ResumeDto> update(Long id);
    Resume findById(Long resumeId);
    void delete(Long id);
}
