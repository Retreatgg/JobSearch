package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
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
public class ResumeUpdateDto {

    @NotNull
    private String title;

    @NotNull
    @NotEmpty
    private String categoryName;

    @NotNull
    @Positive
    private Double salary;

    private List<WorkExperienceInfoDto> workExperienceInfo;
    private List<EducationInfoDto> educationInfo;

    @NotNull
    private List<ContactInfoDto> contacts;

    @NotNull
    private Boolean isActive;
}
