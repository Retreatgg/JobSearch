package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeCreateDto {

    @NotNull
    private String title;

    @NotNull
    private String categoryName;

    @Positive
    private Double salary;

    private List<WorkExperienceInfoDto> workExperienceInfo = new ArrayList<>();
    private List<EducationInfoDto> educationInfo = new ArrayList<>();
    private ContactInfoDto[] contacts = new ContactInfoDto[5];
    private Boolean isActive;
}
