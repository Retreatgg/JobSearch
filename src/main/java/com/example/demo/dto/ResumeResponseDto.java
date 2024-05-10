package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResumeResponseDto {
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
    private Long employerId;
}
