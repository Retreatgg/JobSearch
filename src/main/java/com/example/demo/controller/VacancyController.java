package com.example.demo.controller;

import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;

    @GetMapping("{id}")
    public String getVacancy(Model model, @PathVariable Long id) {
        model.addAttribute("vacancy", vacancyService.getVacancyById(id));
        return "vacancy/vacancy";
    }

    @GetMapping("active")
    public String getActiveVacancies(Model model) {
        model.addAttribute("vacancies", vacancyService.getActiveVacancy());
        return "vacancy/all_active_vacancies";
    }
}
