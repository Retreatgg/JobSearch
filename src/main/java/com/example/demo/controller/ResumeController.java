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

    @GetMapping("category{categoryId}employerId{employerId}")
    public ResponseEntity<ResumeDto> getResumeByCategoryId(@PathVariable long categoryId, @PathVariable long employerId) {
        return ResponseEntity.ok(resumeService.getResumesByCategoryId(categoryId, employerId));
    }

    @GetMapping("applicant{id}employerId{employerId}")
    public ResponseEntity<List<ResumeDto>> getResumesByApplicant(@PathVariable long id, @PathVariable long employerId) {
        return ResponseEntity.ok(resumeService.getResumesByApplicantId(id, employerId));
    }

    @GetMapping("{id}employerId{employerId}")
    public ResponseEntity<ResumeDto> getResumeById(@PathVariable long id, @PathVariable long employerId) {
        return ResponseEntity.ok(resumeService.getResumeById(id, employerId));
    }

    @DeleteMapping("{id}applicant{applicantId}")
    public ResponseEntity<Void> deleteResumeById(@PathVariable long id, @PathVariable long applicantId) {
       if(resumeService.deleteResumeById(id, applicantId)) {
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.notFound().build();
    }

    @PostMapping("applicant{applicantId}")
    public HttpStatus addResume(@RequestBody ResumeDto resumeDto, @PathVariable long applicantId) {
        resumeService.addResume(resumeDto, applicantId);
        return HttpStatus.OK;
    }

    @PutMapping("applicant{applicantId}")
    public HttpStatus editResume(@RequestBody ResumeDto resumeDto, @PathVariable long applicantId) {
        resumeService.editResume(resumeDto, applicantId);
        return HttpStatus.OK;
    }

}
