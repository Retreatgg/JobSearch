package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @Size(min = 4, max = 24, message = "${user.validation.password.size}")
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "${user.validation.password.pattern}"
    )
    private String password;

    @NotEmpty
    @Pattern(regexp="\\d{10}", message="Invalid phone number")
    private String phoneNumber;
    @NotEmpty
    private String accountType;
    private MultipartFile photo;
}
