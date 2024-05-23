package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@Getter
@Setter
public class UserCreateDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;

    private Integer age;

    @NotEmpty
    @Email
    private String email;

    @NotBlank
   /* @Size(min = 4, max = 24, message = "Length must be >= 4 and <= 24")
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Should contain at least one UPPER case letter, one number"
    )*/
    private String password;

    @NotEmpty
    //@Pattern(regexp="\\d{10}", message="Invalid phone number")
    private String phoneNumber;
    @NotEmpty
    private String accountType;
    private MultipartFile photo;
}
