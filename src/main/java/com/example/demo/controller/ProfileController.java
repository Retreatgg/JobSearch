package com.example.demo.controller;

import com.example.demo.dto.UserUpdateDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
