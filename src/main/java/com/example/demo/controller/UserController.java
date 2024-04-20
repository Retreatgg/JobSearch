package com.example.demo.controller;

import com.example.demo.dto.UserCreateDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping("register")
    public String createUser() {
        return "user/create_user";
    }

    @PostMapping("register")
    public String createUser(UserCreateDto user) {
        userService.createUser(user);
        return "redirect:/vacancies/active";
    }
}