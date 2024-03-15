package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("profile")
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("id{id}")
    public HttpStatus editProfile(@RequestBody UserDto userDto, @PathVariable long id) {
        profileService.editProfile(userDto, id);
        return HttpStatus.OK;
    }

}
