package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserUpdateDto;
import com.example.demo.service.MessageService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.UserService;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.demo.enums.AccountType.APPLICANT;


@Controller
@RequiredArgsConstructor
@RequestMapping("profile")
public class ProfileController {

    private final UserService userService;
    private final VacancyService vacancyService;
    private final ResumeService resumeService;
    private final MessageService messageService;

    @PostMapping("edit")
    public String editProfile(Authentication authentication, UserUpdateDto updateDto) {
        userService.editProfile(updateDto, authentication);
        return "redirect:/";
    }

    @GetMapping("edit/{email}")
    public String editProfile(Authentication authentication, Model model, @PathVariable String email) {
        model.addAttribute("user", userService.getUserByEmail(email));
        return "profile/edit_profile";
    }

    @GetMapping("{email}")
    public String getProfile(Authentication auth, Model model, @PathVariable String email) {
        UserDto user = userService.getUserByEmail(email);

        model.addAttribute("user", user);
        model.addAttribute("vacancies", vacancyService.getVacanciesByCompanyName(user.getName()));
        model.addAttribute("resumes", resumeService.getResumesByApplicantId(auth));
        model.addAttribute("image", userService.downloadImage(email));

        if(user.getAccountType().equals(APPLICANT.toString())) {
            model.addAttribute("messages", messageService.getAllMessages(auth));
            model.addAttribute("activeResumesWithResponses", resumeService.getResponsesResumes(user.getId(), auth));
        }

        return "profile/profile";
    }

    @GetMapping("photo/{email}")
    public ResponseEntity<?> getPhoto(@PathVariable String email) {
        return ResponseEntity.ok(userService.downloadImage(email));
    }
}
