package com.example.demo.controller;

import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyUpdateDto;
import com.example.demo.service.CategoryService;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
            @RequestParam(name = "page", defaultValue = "0") String page,
            @RequestParam(name = "size", defaultValue = "5") String perPage,
            @RequestParam(name = "category", required = false) String category)
    {

        if(category == null || category.isEmpty()) {
            model.addAttribute("vacancies", vacancyService.getActiveVacancy(page, perPage, 0));
        } else {
            Long categoryId = categoryService.getCategoryId(category);
            model.addAttribute("vacancies", vacancyService.getActiveVacancy(page, perPage, categoryId));
        }

        model.addAttribute("page", Integer.parseInt(page));
        model.addAttribute("perPage", Integer.parseInt(perPage));
        model.addAttribute("categories", categoryService.categories());
        return "vacancy/all_active_vacancies";
    }

    @GetMapping("add")
    public String addVacancy(Model model) {
        model.addAttribute("categories", categoryService.categories());
        return "vacancy/add_vacancy";
    }

    @PostMapping("add")
    public String addNewVacancy(VacancyDto vacancyDto, Authentication auth) {
        vacancyService.addVacancy(vacancyDto, auth);
        return "redirect:/vacancies/active";
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
        return "vacancy/edit";
    }

    @PostMapping("edit/{id}")
    public String editVacancy(@PathVariable Long id, VacancyUpdateDto vacancyUpdateDto, Authentication auth) {
        vacancyService.editVacancy(vacancyUpdateDto, id, auth);
        return "redirect:/profile";
    }
}
