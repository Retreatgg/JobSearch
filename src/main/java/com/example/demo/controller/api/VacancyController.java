package com.example.demo.controller.api;

import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.model.Vacancy;
import com.example.demo.service.RespondedApplicantService;
import com.example.demo.service.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.enums.AccountType.APPLICANT;

@RestController()
@RequiredArgsConstructor
@RequestMapping("api/vacancies")
public class VacancyController {

    private final VacancyService vacancyService;
    private final RespondedApplicantService respondedApplicantService;

    @GetMapping("")
    public ResponseEntity<List<VacancyDtoForShow>> vacancies() {
        return ResponseEntity.ok(vacancyService.getAllVacancies());
    }

    @GetMapping("responded-vacancy/{id}")
    public ResponseEntity<List<RespondedApplicantsDto>> getRespondedVacancies(@PathVariable long id) {
        return ResponseEntity.ok(respondedApplicantService.respondedApplicants(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<VacancyDtoForShow> getVacancy(@PathVariable Long id) {
        return ResponseEntity.ok(vacancyService.getVacancyById(id));
    }

    @DeleteMapping("{id}")
    public void deleteVacancyById(@PathVariable long id, Authentication auth) {
        vacancyService.deleteVacancyById(id, auth);
    }

    @GetMapping("companies/{name}")
    public ResponseEntity<List<VacancyDtoForShow>> getVacanciesByCompanyName(@PathVariable String name) {
        return ResponseEntity.ok(vacancyService.getVacanciesByCompanyName(name));
    }

    @PostMapping("")
    public ResponseEntity<VacancyDtoForShow> addNewVacancy(@Valid VacancyDto vacancyDto, Authentication auth) {
        return vacancyService.addVacancy(vacancyDto, auth);
    }

    @PatchMapping("{id}")
    public ResponseEntity<VacancyDtoForShow> update(@PathVariable Long id) {
        return vacancyService.update(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<VacancyDtoForShow> editVacancy(@PathVariable Long id, @Valid VacancyUpdateDto vacancyUpdateDto, Authentication auth) {
        return vacancyService.editVacancy(vacancyUpdateDto, id, auth);
    }

    @PostMapping("{id}/respond")
    public void respond(
            @PathVariable Long id,
            @RequestParam(name = "resumeId", required = true) Long resumeId) {
        RespondedApplicantsDto respond = RespondedApplicantsDto.builder()
                .vacancyId(id)
                .resumeId(resumeId)
                .confirmation(false)
                .build();

        respondedApplicantService.createRespondedApplicant(respond);
    }
}