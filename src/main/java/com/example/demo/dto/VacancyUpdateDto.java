package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VacancyUpdateDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private Long categoryId;
    @Positive
    private Double salary;

    @Positive
    private Integer expFrom;
    @Positive
    private Integer expTo;
    @NotNull
    private Boolean isActive;
}
