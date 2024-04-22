package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginDto {

    private String email;
    private String password;
}
