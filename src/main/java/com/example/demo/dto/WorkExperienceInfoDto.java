package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WorkExperienceInfoDto {
    @Positive
    private Integer years;
    @NotNull
    private String companyName;
    @NotNull
    private String position;
    @NotNull
    private String responsibilities;
}
