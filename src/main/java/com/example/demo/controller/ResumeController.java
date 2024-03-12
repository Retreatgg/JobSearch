package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("resumes/{categoryId}")
    public ResponseEntity<ResumeDto> getResumeByName(@PathVariable long categoryId) {
        return ResponseEntity.ok(resumeService.getResumesByCategory(categoryId));
    }

    @GetMapping("resumes/applicant{name}")
    public ResponseEntity<List<ResumeDto>> getResumesByApplicant(@PathVariable String name) {
        return ResponseEntity.ok(resumeService.getResumesByName(name));
    }

}
