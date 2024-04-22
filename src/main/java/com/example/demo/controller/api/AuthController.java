package com.example.demo.controller.api;


import com.example.demo.dto.UserLoginDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("restAuth")
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("login")
    public HttpStatus loginUser(@RequestBody UserLoginDto user) {
        userService.login(user);
        return HttpStatus.OK;
    }
}
