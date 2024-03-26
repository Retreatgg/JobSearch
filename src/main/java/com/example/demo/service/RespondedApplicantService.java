package com.example.demo.service;

import com.example.demo.dto.RespondedApplicantsDto;
import com.example.demo.dto.VacancyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RespondedApplicantService {
    void createRespondedApplicant(RespondedApplicantsDto respondedApplicantsDto);
    List<RespondedApplicantsDto> respondedApplicants(long vacancyId);
}
