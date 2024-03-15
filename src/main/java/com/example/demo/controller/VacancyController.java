package com.example.demo.controller;

import com.example.demo.dto.VacancyDto;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
