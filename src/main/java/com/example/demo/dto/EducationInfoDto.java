package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
public class EducationInfoDto {
    @NotNull
    private String institution;
    @NotNull
    private String program;
    @Past
    private LocalDate startDate;
    @Past
    private LocalDate endDate;
    @NotNull
    private String degree;
}
