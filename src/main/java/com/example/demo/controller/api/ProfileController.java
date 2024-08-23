//package com.example.demo.controller.api;
//
//import com.example.demo.dto.UserDto;
//import com.example.demo.dto.UserUpdateDto;
//import com.example.demo.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//@RestController("restProfile")
//@RequiredArgsConstructor
//@RequestMapping("api/profile")
//public class ProfileController {
//
//    private final UserService userService;
//
//    @PostMapping("edit")
//    public HttpStatus editProfile(Authentication authentication, UserUpdateDto updateDto) {
//        userService.editProfile(updateDto, authentication);
//        return HttpStatus.OK;
//    }
//
//    @GetMapping("{email}")
//    public ResponseEntity<UserDto> getProfile(Authentication authentication, @PathVariable String email) {
//        return ResponseEntity.ok(userService.getUserByEmail(email));
//    }
//
//}
