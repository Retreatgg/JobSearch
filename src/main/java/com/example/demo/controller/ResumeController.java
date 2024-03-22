package com.example.demo.controller;

import com.example.demo.dto.ResumeCreateDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.ResumeUpdateDto;
import com.example.demo.service.ResumeService;
import jakarta.validation.Valid;
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

    @GetMapping("category{categoryId}employerId{employerId}")
    public ResponseEntity<ResumeDto> getResumeByCategoryId(@PathVariable long categoryId, @PathVariable long employerId) {
        return ResponseEntity.ok(resumeService.getResumesByCategoryId(categoryId, employerId));
    }

    @GetMapping("applicant{id}employerId{employerId}")
    public ResponseEntity<List<ResumeDto>> getResumesByApplicant(@PathVariable long id, @PathVariable long employerId) {
        return ResponseEntity.ok(resumeService.getResumesByApplicantId(id, employerId));
    }

    @GetMapping("{id}employerId{employerId}")
    public ResponseEntity<?> getResumeById(@PathVariable long id, @PathVariable long employerId) {
        return ResponseEntity.ok(resumeService.getResumeById(id, employerId));
    }

    @DeleteMapping("{id}applicant{applicantId}")
    public ResponseEntity<?> deleteResumeById(@PathVariable long id, @PathVariable long applicantId) {
           return ResponseEntity.ok(resumeService.deleteResumeById(id, applicantId));
    }

    @PostMapping("applicant{applicantId}")
    public HttpStatus addResume(@RequestBody @Valid ResumeCreateDto resumeDto, @PathVariable long applicantId) {
        resumeService.addResume(resumeDto, applicantId);
        return HttpStatus.OK;
    }

    @PutMapping("{id}applicant{applicantId}")
    public HttpStatus editResume(@RequestBody @Valid ResumeUpdateDto resumeDto, @PathVariable long applicantId, @PathVariable long id) {
        resumeService.editResume(resumeDto, id, applicantId);
        return HttpStatus.OK;
    }


}
