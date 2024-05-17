package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeCreateDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String categoryName;

    @Positive
    private Double salary;

    private List<WorkExperienceInfoDto> workExperienceInfo = new ArrayList<>();
    private List<EducationInfoDto> educationInfo = new ArrayList<>();
    private ContactInfoDto[] contacts = new ContactInfoDto[5];
    private Boolean isActive;
}
