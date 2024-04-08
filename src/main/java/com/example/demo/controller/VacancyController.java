package com.example.demo.controller;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dto.VacancyDto;
import com.example.demo.service.CategoryService;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;
    private final CategoryService categoryService;

    @GetMapping("{id}")
    public String getVacancy(Model model, @PathVariable Long id) {
        model.addAttribute("vacancy", vacancyService.getVacancyById(id));
        return "vacancy/vacancy";
    }

    @GetMapping("active")
    public String getActiveVacancies(
            Model model,
            @RequestParam(name = "page", defaultValue = "1") String page,
            @RequestParam(name = "size", defaultValue = "5") String perPage)
    {
        model.addAttribute("vacancies", vacancyService.getActiveVacancy(page, perPage));
        model.addAttribute("page", Integer.parseInt(page));
        model.addAttribute("perPage", perPage);
        return "vacancy/all_active_vacancies";
    }

    @GetMapping("add")
    public String addVacancy(Model model) {
        model.addAttribute("categories", categoryService.categories());
        return "vacancy/add_vacancy";
    }
    @PostMapping("add")
    public String addNewVacancy(VacancyDto vacancyDto) {
        return "vacancy/add_vacancy";
    }
}
