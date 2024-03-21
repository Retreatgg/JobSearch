package com.example.demo.service;

import com.example.demo.dto.ResumeCreateDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResumeService {
    ResumeDto getResumesByCategoryId(Long id, long userId);
    List<ResumeDto> getResumesByApplicantId(long id, long userId);

    ResumeDto getResumeById(Long id, long userId);

    boolean deleteResumeById(Long id, long userId);
    void addResume(ResumeCreateDto resumeDto, long userId);
    void editResume(ResumeDto resumeDto, long userId);
}
