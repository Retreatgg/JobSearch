package com.example.demo.controller;


import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyDtoForShow;
import com.example.demo.model.Vacancy;
import com.example.demo.service.CategoryService;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable)
    {
        Page<VacancyDtoForShow> page = vacancyService.getAllVacancies(pageable);

        model.addAttribute("pageSize", pageable.getPageSize());
        model.addAttribute("pageNumber", pageable.getPageNumber());
        model.addAttribute("page", page);
        model.addAttribute("categories", categoryService.categories());
        return "vacancy/all_active_vacancies";
    }
}
