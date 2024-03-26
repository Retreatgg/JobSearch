package com.example.demo.service.impl;

import com.example.demo.dao.RespondedApplicantsDao;
import com.example.demo.dto.RespondedApplicantsDto;
import com.example.demo.model.RespondedApplicant;
import com.example.demo.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RespondedApplicantServiceImpl implements RespondedApplicantService {
    private final RespondedApplicantsDao respondedApplicantsDao;
    @Override
    public void createRespondedApplicant(RespondedApplicantsDto respondedApplicantsDto) {
        RespondedApplicant respondedApplicant = new RespondedApplicant();
        respondedApplicant.setResumeId(respondedApplicantsDto.getResumeId());
        respondedApplicant.setConfirmation(respondedApplicantsDto.getConfirmation());
        respondedApplicant.setVacancyId(respondedApplicantsDto.getVacancyId());
        respondedApplicantsDao.createRespondedApplicants(respondedApplicant);
    }
}
