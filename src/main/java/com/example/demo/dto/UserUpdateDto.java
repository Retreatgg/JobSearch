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
public class UserUpdateDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @Min(18)
    private Integer age;

    @NotBlank
    @Size(min = 4, max = 24, message = "Length must be >= 4 and <= 24")
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Should contain at least one UPPER case letter, one number"
    )
    private String password;

    @NotEmpty
    @Pattern(regexp="\\d{10}", message="Invalid phone number")
    private String phoneNumber;
    private MultipartFile avatar;
}
