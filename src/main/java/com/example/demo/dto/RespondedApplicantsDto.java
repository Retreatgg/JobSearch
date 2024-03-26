package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class RespondedApplicantsDto {
    private Long resumeId;
    private Long vacancyId;
    private Boolean confirmation;
}
