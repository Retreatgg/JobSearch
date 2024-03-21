package com.example.demo.dto;

import com.example.demo.model.Category;
import com.example.demo.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
public class VacancyDto {
    private Long id;
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
    @NotEmpty
    private Long authorId;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

}
