package com.example.demo.controller;

import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyUpdateDto;
import com.example.demo.service.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("vacancies")
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping("")
    public ResponseEntity<List<VacancyDto>> getAllVacancies() {
        return ResponseEntity.ok(vacancyService.getAllVacancies());
    }

    @GetMapping("responded-vacancies")
    public ResponseEntity<List<VacancyDto>> getRespondedVacancies() {
        return ResponseEntity.ok(vacancyService.getRespondedVacancies());
    }

    @GetMapping("active")
    public ResponseEntity<List<VacancyDto>> getActiveVacancies() {
        return ResponseEntity.ok(vacancyService.getActiveVacancy());
    }

    @DeleteMapping("{id}")
    public void deleteVacancyById(@PathVariable long id, Authentication auth) {
        vacancyService.deleteVacancyById(id, auth);
    }

    @PostMapping("")
    public HttpStatus addVacancy(@RequestBody @Valid VacancyDto vacancyDto, Authentication auth) {
        vacancyService.addVacancy(vacancyDto, auth);
        return HttpStatus.OK;
    }

    @PutMapping("{id}")
    public HttpStatus editVacancy(@RequestBody @Valid VacancyUpdateDto vacancyDto, @PathVariable long id, Authentication auth) {
        vacancyService.editVacancy(vacancyDto, id, auth);
        return HttpStatus.OK;
    }

    @GetMapping("company/{name}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCompanyName(@PathVariable String name) {
        return ResponseEntity.ok(vacancyService.getVacanciesByCompanyName(name));
    }

    @PostMapping("respond/{id}")
    public HttpStatus respond(@PathVariable long id, Authentication authentication) {
        vacancyService.respond(id, authentication);
        return HttpStatus.OK;
    }
}
