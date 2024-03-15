package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("delete{id}")
    public void deleteResumeById(@PathVariable long id) {
        resumeService.deleteResumeById(id);
    }

    @PostMapping("add")
    public HttpStatus addResume(@RequestBody ResumeDto resumeDto) {
        resumeService.addResume(resumeDto);
        return HttpStatus.OK;
    }

}
