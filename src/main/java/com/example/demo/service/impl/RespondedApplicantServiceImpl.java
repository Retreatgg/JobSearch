package com.example.demo.service.impl;

import com.example.demo.dao.RespondedApplicantsDao;
import com.example.demo.dto.RespondedApplicantsDto;
import com.example.demo.model.RespondedApplicant;
import com.example.demo.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<RespondedApplicantsDto> respondedApplicants(long vacancyId) {
        List<RespondedApplicant> respondedApplicants = respondedApplicantsDao.getRespondedVacancies(vacancyId);
        List<RespondedApplicantsDto> dtos = new ArrayList<>();
        respondedApplicants.forEach(e -> {
            dtos.add(RespondedApplicantsDto.builder()
                            .confirmation(e.getConfirmation())
                            .resumeId(e.getResumeId())
                            .vacancyId(e.getVacancyId())
                            .build());
        });

        return dtos;
    }
}
