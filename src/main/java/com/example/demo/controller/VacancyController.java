package com.example.demo.controller;

import com.example.demo.dto.RespondedApplicantsDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyUpdateDto;
import com.example.demo.model.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.RespondedApplicantService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.VacancyService;
import com.example.demo.util.UserUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.enums.AccountType.APPLICANT;

@Controller
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;
    private final ResumeService resumeService;
    private final CategoryService categoryService;
    private final RespondedApplicantService respondedApplicantService;
    private final UserUtil userUtil;

    @GetMapping("{id}")
    public String getVacancy(Model model, @PathVariable Long id, Authentication auth) {
        model.addAttribute("vacancy", vacancyService.getVacancyById(id));
        if (auth != null) {
            User user = userUtil.getUserByAuth(auth);
            if(user.getAccountType().equals(APPLICANT.toString())) {
                List<ResumeDto> resumes = resumeService.getResumesByApplicantId(auth);
                if (!resumes.isEmpty()) {
                    model.addAttribute("auth", auth);
                    model.addAttribute("applicantResumes", resumes);
                }
            }
        }
        return "vacancy/vacancy";
    }

    @GetMapping("add")
    public String addVacancy(Model model) {
        model.addAttribute("categories", categoryService.categories());
        return "vacancy/add_vacancy";
    }

    @PostMapping("add")
    public String addNewVacancy(@Valid VacancyDto vacancyDto, Authentication auth) {
        vacancyService.addVacancy(vacancyDto, auth);
        return "redirect:/profile";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable Long id) {
        vacancyService.update(id);
        return "redirect:/profile";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("categories", categoryService.categories());
        model.addAttribute("id", id);
        model.addAttribute("vacancy", vacancyService.getVacancyById(id));
        return "vacancy/edit";
    }

    @PostMapping("edit/{id}")
    public String editVacancy(@PathVariable Long id, VacancyUpdateDto vacancyUpdateDto, Authentication auth) {
        vacancyService.editVacancy(vacancyUpdateDto, id, auth);
        return "redirect:/profile";
    }


    @PostMapping("respond/{id}")
    public String respond(
            @PathVariable Long id,
            @RequestParam(name = "resumeId", required = true) Long resumeId)
    {
        RespondedApplicantsDto respond = RespondedApplicantsDto.builder()
                .vacancyId(id)
                .resumeId(resumeId)
                .confirmation(false)
                .build();

        respondedApplicantService.createRespondedApplicant(respond);
        return "redirect:/";
    }
}
