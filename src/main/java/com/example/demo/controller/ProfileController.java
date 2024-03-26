package com.example.demo.controller;

import com.example.demo.dto.UserUpdateDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("profile")
public class ProfileController {
    private final UserService userService;
    @PostMapping("{email}")
    public HttpStatus editProfile(Authentication auth, @PathVariable String email, UserUpdateDto updateDto) {
        userService.editProfile(updateDto, auth, email);
        return HttpStatus.OK;
    }
}
