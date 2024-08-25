package com.example.demo.dto;

import com.example.demo.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class VacancyDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String categoryName;
    @Positive
    private Double salary;
    @Positive
    private Integer expFrom;
    @Positive
    private Integer expTo;
    @NotNull
    private Boolean isActive;

}
