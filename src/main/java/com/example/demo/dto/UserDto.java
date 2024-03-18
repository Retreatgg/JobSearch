package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private Integer age;
    private String email;
    private String phoneNumber;
    private MultipartFile avatar;
    private String accountType;
}
