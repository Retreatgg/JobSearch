package com.example.demo.service;

import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResumeService {
    ResumeDto getResumesByCategoryId(Long id, long userId);
    List<ResumeDto> getResumesByName(String name, long userId);

    ResumeDto getResumeById(Long id, long userId);
    List<ResumeDto> getResumesByApplicantId(Long id, long uesrId);

    boolean deleteResumeById(Long id, long userId);
    void addResume(ResumeDto resumeDto, long userId);
    void editResume(ResumeDto resumeDto, long id, long userId);
}
