//package com.example.demo.controller;
//
//
//import com.example.demo.service.CategoryService;
//import com.example.demo.service.VacancyService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequestMapping("")
//@RequiredArgsConstructor
//public class MainController {
//
//    private final VacancyService vacancyService;
//    private final CategoryService categoryService;
//
////    @GetMapping
////    public String getActiveVacancies(
////            Model model,
////            @RequestParam(name = "category", required = false) String category,
////            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
////
////        Sort sort = pageable.getSort();
////        if (!sort.isEmpty()) {
////            if (category == null || category.isEmpty()) {
////                model.addAttribute("page", vacancyService.getAllVacancies(pageable, 0L));
////            } else {
////                Long categoryId = categoryService.getCategoryId(category);
////                model.addAttribute("page", vacancyService.getAllVacancies(pageable, categoryId));
////            }
////            model.addAttribute("pageSize", pageable.getPageSize());
////            model.addAttribute("pageNumber", pageable.getPageNumber());
////            model.addAttribute("categories", categoryService.categories());
////            if (category != null) {
////                model.addAttribute("category", category);
////            }
////            if (sort != null) {
////                List<String> sorts = new ArrayList();
////                sort.forEach(e -> {
////                    sorts.add(e.getProperty());
////                });
////                sort.forEach(Sort.Order::getProperty);
////                model.addAttribute("sort", sorts.get(0));
////            }
////        }
////
////        return "vacancy/all_active_vacancies";
////    }
//}
