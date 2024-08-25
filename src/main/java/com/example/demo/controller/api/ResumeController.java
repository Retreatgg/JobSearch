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
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
@RequestMapping("api/resumes")
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping()
    public ResponseEntity<Page<ResumeDto>> getAllResumes(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable)
    {
        return ResponseEntity.ok(resumeService.getAllResumes(pageable));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResumeDto> editResume(@PathVariable Long id, @RequestBody ResumeUpdateDto resumeUpdateDto) {
        return resumeService.edit(resumeUpdateDto, id);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<ResumeDto> getResumeByCategoryId(@PathVariable long categoryId) {
        return ResponseEntity.ok(resumeService.getResumesByCategoryId(categoryId));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getResumeById(@PathVariable long id) {
        return ResponseEntity.ok(resumeService.getResumeById(id));
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteResumeById(@PathVariable Long id) {
        resumeService.delete(id);
        return HttpStatus.OK;
    }

    @PostMapping("")
    public ResponseEntity<ResumeDto> addResume(@RequestBody @Valid ResumeCreateDto resumeDto) {
        return resumeService.add(resumeDto);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ResumeDto> updateResume(@PathVariable Long id) {
        return resumeService.update(id);
    }


}
