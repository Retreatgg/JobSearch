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
    @PostMapping("{id}")
    public HttpStatus editProfile(@PathVariable long id, Authentication authentication, UserUpdateDto updateDto) {
        userService.editProfile(updateDto, authentication);
        return HttpStatus.OK;
    }
}
