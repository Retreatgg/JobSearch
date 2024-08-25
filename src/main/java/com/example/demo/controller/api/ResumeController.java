package com.example.demo.controller.api;

import com.example.demo.dto.ResumeCreateDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.ResumeUpdateDto;
import com.example.demo.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping("api/resumes")
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping()
    public ResponseEntity<Page<ResumeDto>> getAllResumes(
            Authentication authentication,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable)
    {
        return ResponseEntity.ok(resumeService.getAllResumes(authentication, pageable));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResumeDto> editResume(@PathVariable Long id, @RequestBody ResumeUpdateDto resumeUpdateDto) {
        return resumeService.editResume(resumeUpdateDto, id);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<ResumeDto> getResumeByCategoryId(@PathVariable long categoryId, Authentication auth) {
        return ResponseEntity.ok(resumeService.getResumesByCategoryId(categoryId, auth));
    }

    @GetMapping("/applicant")
    public ResponseEntity<List<ResumeDto>> getResumesByApplicant( Authentication auth) {
        return ResponseEntity.ok(resumeService.getResumesByApplicantId(auth));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getResumeById(@PathVariable long id, Authentication auth) {
        return ResponseEntity.ok(resumeService.getResumeById(id, auth));
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<?> deleteResumeById(@PathVariable long id, Authentication auth) {
//        return ResponseEntity.ok(resumeService.deleteResumeById(id, auth));
//    }

    @PostMapping("")
    public ResponseEntity<ResumeDto> addResume(@RequestBody @Valid ResumeCreateDto resumeDto, Authentication auth) {
        return resumeService.addResume(resumeDto);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<ResumeDto> updateResume(@PathVariable Long id) {
        return resumeService.updateResume(id);
    }


}
