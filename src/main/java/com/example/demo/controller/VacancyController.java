package com.example.demo.controller;

import com.example.demo.dto.VacancyDto;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("vacancies")
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping("applicant{applicantId}")
    public ResponseEntity<List<VacancyDto>> getAllVacancies(@PathVariable long applicantId) {
        return ResponseEntity.ok(vacancyService.getAllVacancies(applicantId));
    }

    @GetMapping("category{name}applicant{applicantId}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCategory(@PathVariable String name, @PathVariable long applicantId) {
        return ResponseEntity.ok(vacancyService.getVacanciesByCategory(name, applicantId));
    }

    @GetMapping("responded-vacancies/applicant{applicantId}")
    public ResponseEntity<List<VacancyDto>> getRespondedVacancies(@PathVariable long applicantId) {
        return ResponseEntity.ok(vacancyService.getRespondedVacancies(applicantId));
    }

    @GetMapping("authorId{id}applicant{applicantId}")
    public ResponseEntity<List<VacancyDto>> getVacancyByAuthorId(@PathVariable Long id, @PathVariable long applicantId) {
        return ResponseEntity.ok(vacancyService.getVacancyByAuthorId(id, applicantId));
    }

    @GetMapping("active/applicant{applicantId}")
    public ResponseEntity<List<VacancyDto>> getActiveVacancies(@PathVariable long applicantId) {
        return ResponseEntity.ok(vacancyService.getActiveVacancy(applicantId));
    }

    @DeleteMapping("{id}employer{employerId}")
    public void deleteResumeById(@PathVariable long id, @PathVariable long employerId) {
        vacancyService.deleteVacancyById(id, employerId);
    }

    @PostMapping("employer{employerId}")
    public HttpStatus addResume(@RequestBody VacancyDto vacancyDto, @PathVariable long employerId) {
        vacancyService.addVacancy(vacancyDto, employerId);
        return HttpStatus.OK;
    }

    @PutMapping("{id}employer{employerId}")
    public HttpStatus editResume(@RequestBody VacancyDto vacancyDto, @PathVariable long id, @PathVariable long employerId) {
        vacancyService.editVacancy(vacancyDto, id, employerId);
        return HttpStatus.OK;
    }

    @GetMapping("name_company{name}applicant{applicantId}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCompanyName(@PathVariable String name, @PathVariable long applicantId) {
        return ResponseEntity.ok(vacancyService.getVacanciesByCompanyName(name, applicantId));
    }
}
