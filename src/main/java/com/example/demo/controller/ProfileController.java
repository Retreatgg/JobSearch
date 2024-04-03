package com.example.demo.controller;

import com.example.demo.dto.UserUpdateDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("profile")
public class ProfileController {
    private final UserService userService;
    @PostMapping("edit")
    public String editProfile(Authentication authentication, UserUpdateDto updateDto) {
        userService.editProfile(updateDto, authentication);
        return "redirect:/";
    }

    @GetMapping("edit")
    public String editProfile(Model model){
        model.addAttribute("user", userService.getUserByEmail("bob@example.com"));
        return "profile/edit_profile";
    }

    @GetMapping
    public String getProfile(Authentication authentication, Model model) {
        model.addAttribute("user", userService.getUserByEmail("bob@example.com"));
        return "profile/profile";
    }
}
