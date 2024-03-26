package com.example.demo.service;

import com.example.demo.dto.RespondedApplicantsDto;
import org.springframework.stereotype.Service;

@Service
public interface RespondedApplicantService {
    void createRespondedApplicant(RespondedApplicantsDto respondedApplicantsDto);
}
