package com.example.demo.controller;


import com.example.demo.dto.VacancyDtoForShow;
import com.example.demo.service.CategoryService;
import com.example.demo.service.VacancyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
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
            @RequestParam(name = "category", required = false) String category,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            Authentication auth)
    {

        Sort sort = pageable.getSort();
        if(!sort.isEmpty()) {
            if(category == null || category.isEmpty()) {
                model.addAttribute("page", vacancyService.getAllVacancies(pageable, 0L));
            } else {
                Long categoryId = categoryService.getCategoryId(category);
                model.addAttribute("page", vacancyService.getAllVacancies(pageable, categoryId));
            }
            model.addAttribute("pageSize", pageable.getPageSize());
            model.addAttribute("pageNumber", pageable.getPageNumber());
            model.addAttribute("categories", categoryService.categories());
        }

        if(auth != null) {
            model.addAttribute("auth", auth);
        }

        return "vacancy/all_active_vacancies";
    }
}
