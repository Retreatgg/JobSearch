package com.example.demo.controller;


import com.example.demo.service.CategoryService;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class MainController {

    private final VacancyService vacancyService;
    private final CategoryService categoryService;

    @GetMapping
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
}
