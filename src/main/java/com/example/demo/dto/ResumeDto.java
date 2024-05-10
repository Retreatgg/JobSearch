package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
public class ResumeDto {
    private Long id;
    @NotEmpty
    private Long applicant;
    @NotEmpty
    private String name;
    @NotEmpty
    private Long categoryId;
    @NotEmpty
    private Double salary;
    @NotEmpty
    private Boolean isActive;
    private String createdDate;
    private String updateTime;
}
