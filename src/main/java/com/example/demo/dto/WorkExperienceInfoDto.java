package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
