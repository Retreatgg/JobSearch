package com.example.demo.controller;

import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyDtoForShow;
import com.example.demo.service.CategoryService;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @RequestParam(name = "page", defaultValue = "0") String page,
            @RequestParam(name = "size", defaultValue = "5") String perPage,
            @RequestParam(name = "category", required = false) String category)
    {

        List<VacancyDtoForShow> vacancies;
        if(category == null || category.isEmpty()) {
            vacancies = vacancyService.getActiveVacancy(page, perPage, 0);
            model.addAttribute("vacancies", vacancies);
        } else {
            Long categoryId = categoryService.getCategoryId(category);
            vacancies = vacancyService.getActiveVacancy(page, perPage, categoryId);
            model.addAttribute("vacancies", vacancies);
        }

        model.addAttribute("page", Integer.parseInt(page));
        model.addAttribute("perPage", Integer.parseInt(perPage));
        model.addAttribute("categories", categoryService.categories());
        model.addAttribute("maxPage", vacancies.size() / Integer.parseInt(perPage));
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
