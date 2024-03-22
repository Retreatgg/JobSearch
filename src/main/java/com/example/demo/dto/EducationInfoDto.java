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

    private String institution;

    private String program;

    private LocalDate startDate;

    private LocalDate endDate;
    private String degree;
}
