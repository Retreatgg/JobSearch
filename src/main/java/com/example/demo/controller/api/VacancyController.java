package com.example.demo.controller.api;

import com.example.demo.dto.RespondedApplicantsDto;
import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyDtoForShow;
import com.example.demo.dto.VacancyUpdateDto;
import com.example.demo.service.RespondedApplicantService;
import com.example.demo.service.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> deleteVacancyById(@PathVariable long id) {
        vacancyService.deleteVacancyById(id);
        return ResponseEntity.ok("Vacancy successfully was deleted");
    }

    @GetMapping("companies/{name}")
    public ResponseEntity<List<VacancyDtoForShow>> getVacanciesByCompanyName(@PathVariable String name) {
        return ResponseEntity.ok(vacancyService.getVacanciesByCompanyName(name));
    }

    @PostMapping("")
    public ResponseEntity<VacancyDtoForShow> addNewVacancy(@RequestBody @Valid VacancyDto vacancyDto) {
        return vacancyService.addVacancy(vacancyDto);
    }

    @PatchMapping("{id}")
    public ResponseEntity<VacancyDtoForShow> update(@PathVariable Long id) {
        return vacancyService.update(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<VacancyDtoForShow> editVacancy(@PathVariable Long id, @RequestBody @Valid VacancyUpdateDto vacancyUpdateDto) {
        return vacancyService.editVacancy(vacancyUpdateDto, id);
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