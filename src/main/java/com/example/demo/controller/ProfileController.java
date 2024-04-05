package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserUpdateDto;
import com.example.demo.service.ResumeService;
import com.example.demo.service.UserService;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.demo.enums.AccountType.APPLICANT;
import static com.example.demo.enums.AccountType.EMPLOYER;

@Controller
@RequiredArgsConstructor
@RequestMapping("profile")
public class ProfileController {

    private final UserService userService;
    private final VacancyService vacancyService;
    private final ResumeService resumeService;

    @PostMapping("edit")
    public String editProfile(Authentication authentication, UserUpdateDto updateDto) {
        userService.editProfile(updateDto, authentication);
        return "redirect:/";
    }

    @GetMapping("edit/{email}")
    public String editProfile(Authentication authentication, Model model, @PathVariable String email) {
        model.addAttribute("user", userService.getUserByEmail(authentication, email));
        return "profile/edit_profile";
    }

    @GetMapping("/{email}")
    public String getProfile(Authentication auth, Model model, @PathVariable String email) {
        UserDto user = userService.getUserByEmail(auth, email);
        model.addAttribute("user", user);

        if (user.getAccountType().equals(EMPLOYER.toString())) {
            model.addAttribute("vacancies", vacancyService.getVacanciesByCompanyName(user.getName()));
        } else if (user.getAccountType().equals(APPLICANT.toString())) {
            model.addAttribute("resumes", resumeService.getResumesByApplicantId(user.getId(), auth));
        }

        return "profile/profile";
    }
}
