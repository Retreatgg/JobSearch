package com.example.demo.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationInfoDto {

    private String institution;

    private String program;

    private LocalDate startDate;

    private LocalDate endDate;
    private String degree;
}
