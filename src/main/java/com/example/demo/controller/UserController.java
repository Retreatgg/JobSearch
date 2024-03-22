package com.example.demo.controller;

import com.example.demo.dto.UserCreateDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @GetMapping("email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("phone/{phoneNumber}")
    public ResponseEntity<UserDto> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(userService.getUserByPhoneNumber(phoneNumber));
    }

    @GetMapping("is-exist{email}")
    public ResponseEntity<Boolean> isUserExistsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.isUserExistsByEmail(email));
    }

    @PostMapping()
    public HttpStatus createUser(@RequestBody UserCreateDto userCreateDto) {
        userService.createUser(userCreateDto);
        return HttpStatus.OK;
    }
}
