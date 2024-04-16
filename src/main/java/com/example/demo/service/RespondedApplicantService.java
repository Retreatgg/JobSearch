package com.example.demo.service;

import com.example.demo.dto.RespondedApplicantsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RespondedApplicantService {
    void createRespondedApplicant(RespondedApplicantsDto respondedApplicantsDto);
    List<RespondedApplicantsDto> respondedApplicants(long vacancyId);
    List<Long> getRespondIdByResume(Long resumeId);
    List<RespondedApplicantsDto> getResponsesByApplicantId(Long applicantId);
}
