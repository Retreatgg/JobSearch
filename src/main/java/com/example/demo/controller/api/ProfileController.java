/*package com.example.demo.controller.api;

import com.example.demo.dto.UserUpdateDto;
import com.example.demo.service.ResumeService;
import com.example.demo.service.UserService;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("profile")
public class ProfileController {
    private final UserService userService;
    private final VacancyService vacancyService;
    private final ResumeService resumeService;

    /*@PostMapping("edit")
    public String editProfile(Authentication authentication, UserUpdateDto updateDto) {
        userService.editProfile(updateDto, authentication);
        return "redirect:/";
    }

    @GetMapping("edit")
    public String editProfile(Model model){
        model.addAttribute("user", userService.getUserByEmail());
        return "profile/edit_profile";
    }

    @GetMapping
    public String getProfile(Authentication authentication, Model model) {
        model.addAttribute("user", userService.getUserByEmail("bob@example.com"));
        model.addAttribute("vacancies", vacancyService.getVacanciesByCompanyName("Bob"));
        return "profile/profile";
    }
}
*/