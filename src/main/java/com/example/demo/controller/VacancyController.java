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

    @GetMapping
    public ResponseEntity<List<VacancyDto>> getAllVacancies() {
        return ResponseEntity.ok(vacancyService.getAllVacancies());
    }

    @GetMapping("category{name}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCategory(@PathVariable String name) {
        return ResponseEntity.ok(vacancyService.getVacanciesByCategory(name));
    }

    @GetMapping("responded-vacancies")
    public ResponseEntity<List<VacancyDto>> getRespondedVacancies() {
        return ResponseEntity.ok(vacancyService.getRespondedVacancies());
    }

    @GetMapping("authorId{id}")
    public ResponseEntity<List<VacancyDto>> getVacancyByAuthorId(@PathVariable Long id) {
        return ResponseEntity.ok(vacancyService.getVacancyByAuthorId(id));
    }

    @GetMapping("active")
    public ResponseEntity<List<VacancyDto>> getActiveVacancies() {
        return ResponseEntity.ok(vacancyService.getActiveVacancy());
    }

    @DeleteMapping("{id}")
    public void deleteResumeById(@PathVariable long id) {
        vacancyService.deleteVacancyById(id);
    }

    @PostMapping("")
    public HttpStatus addResume(@RequestBody VacancyDto vacancyDto) {
        vacancyService.addVacancy(vacancyDto);
        return HttpStatus.OK;
    }

    @PutMapping("{id}")
    public HttpStatus editResume(@RequestBody VacancyDto vacancyDto, @PathVariable long id) {
        vacancyService.editVacancy(vacancyDto, id);
        return HttpStatus.OK;
    }
}
