package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("resumes")
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("category{categoryId}")
    public ResponseEntity<ResumeDto> getResumeByCategoryId(@PathVariable long categoryId) {
        return ResponseEntity.ok(resumeService.getResumesByCategoryId(categoryId));
    }

    @GetMapping("applicant{name}")
    public ResponseEntity<List<ResumeDto>> getResumesByApplicant(@PathVariable String name) {
        return ResponseEntity.ok(resumeService.getResumesByName(name));
    }

    @GetMapping("id{id}")
    public ResponseEntity<ResumeDto> getResumeById(@PathVariable long id) {
        return ResponseEntity.ok(resumeService.getResumeById(id));
    }

    @GetMapping("applicantId{id}")
    public ResponseEntity<List<ResumeDto>> getResumesByApplicantId(@PathVariable long id) {
        return ResponseEntity.ok(resumeService.getResumesByApplicantId(id));
    }

}
