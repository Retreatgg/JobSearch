package com.example.demo.controller.api;

import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("images")
public class ImageController {

    private final UserService userService;

    @GetMapping("download/{email}")
    public ResponseEntity<?> download(@PathVariable String email) {
        return userService.downloadImage(email);
    }


}
