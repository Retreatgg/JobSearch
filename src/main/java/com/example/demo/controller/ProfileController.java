package com.example.demo.controller;

import com.example.demo.dto.UserUpdateDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("profile")
public class ProfileController {
    private final UserService userService;
    @PostMapping("{id}")
    public HttpStatus editProfile(@PathVariable long id, Authentication authentication, UserUpdateDto updateDto) {
        userService.editProfile(updateDto, authentication);
        return HttpStatus.OK;
    }

    @GetMapping
    public String getProfile(Authentication authentication, Model model) {
        model.addAttribute("user", userService.getUserByEmail("john@example.com"));
        return "profile/profile";
    }
}
