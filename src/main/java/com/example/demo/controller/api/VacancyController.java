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

 @RestController("restVacancy")
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

//    @GetMapping("{id}")
//    public Vacancy getVacancy(@PathVariable Long id) {
//        return vacancyService.getVacancyById(id);
//    }

     @GetMapping("{id}")
     public ResponseEntity<VacancyDto> getVacancy(@PathVariable Long id) {
       return ResponseEntity.ok(vacancyService.getVacancyById(id));
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
    public ResponseEntity<List<VacancyDtoForShow>> getVacanciesByCompanyName(@PathVariable String name) {
        return ResponseEntity.ok(vacancyService.getVacanciesByCompanyName(name));
    }
}