package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @Min(18)
    private Integer age;

    @NotEmpty
    @Email
    private String email;

    @NotBlank
    @Size(min = 4, max = 24, message = "Length must be >= 4 and <= 24")
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Should contain at least one UPPER case letter, one number"
    )
    private String password;
    @NotEmpty
    private String phoneNumber;
    private MultipartFile avatar;
    @NotEmpty
    private String accountType;
}
