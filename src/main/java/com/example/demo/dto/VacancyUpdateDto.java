package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    private Long categoryId;
    @NotEmpty
    private Double salary;

    @NotEmpty
    @Positive
    private Integer expFrom;
    @NotEmpty
    @Positive
    private Integer expTo;
    @NotEmpty
    private Boolean isActive;
}
