package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
@Getter
@Setter
public class ResumeCreateDto {

    @NotNull
    private String title;

    @NotNull
    private String categoryName;

    @Positive
    private Double salary;

    private List<WorkExperienceInfoDto> workExperienceInfo;
    private List<EducationInfoDto> educationInfo;
    private ContactInfoDto contacts;
    private Boolean isActive;
}
