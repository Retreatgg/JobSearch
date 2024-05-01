package com.example.demo.service.impl;

import com.example.demo.dao.RespondedApplicantsDao;
import com.example.demo.dto.RespondedApplicantsDto;
import com.example.demo.model.RespondedApplicant;
import com.example.demo.repository.RespondedApplicantsRepository;
import com.example.demo.repository.ResumeRepository;
import com.example.demo.repository.VacancyRepository;
import com.example.demo.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RespondedApplicantServiceImpl implements RespondedApplicantService {

    private final RespondedApplicantsDao respondedApplicantsDao;
    private final RespondedApplicantsRepository respondedApplicantsRepository;
    private final ResumeRepository resumeRepository;
    private final VacancyRepository vacancyRepository;
    @Override
    public void createRespondedApplicant(RespondedApplicantsDto respondedApplicantsDto) {
        RespondedApplicant respondedApplicant = RespondedApplicant.builder()
                .confirmation(respondedApplicantsDto.getConfirmation())
                .resume(resumeRepository.findById(respondedApplicantsDto.getResumeId()).get())
                .vacancy(vacancyRepository.findById(respondedApplicantsDto.getVacancyId()).get())
                .build();

        respondedApplicantsRepository.save(respondedApplicant);
    }

    @Override
    public List<RespondedApplicantsDto> respondedApplicants(long vacancyId) {
        List<RespondedApplicant> respondedApplicants = respondedApplicantsRepository.findRespondedApplicantByVacancyId(vacancyId);
        List<RespondedApplicantsDto> dtos = new ArrayList<>();
        respondedApplicants.forEach(e -> {
            dtos.add(RespondedApplicantsDto.builder()
                            .confirmation(e.getConfirmation())
                            .resumeId(e.getResume().getId())
                            .vacancyId(e.getVacancy().getId())
                            .build());
        });

        return dtos;
    }

    @Override
    public List<Long> getRespondIdByResume(Long resumeId) {
        return respondedApplicantsRepository.findIdByResumeId(resumeId);
    }

    @Override
    public List<RespondedApplicantsDto> getResponsesByApplicantId(Long applicantId) {
        List<RespondedApplicant> responses = respondedApplicantsRepository.findResponsesByApplicantId(applicantId);
        List<RespondedApplicantsDto> responseDto = new ArrayList<>();

        responses.forEach(response -> {
            responseDto.add(RespondedApplicantsDto.builder()
                            .vacancyId(response.getVacancy().getId())
                            .resumeId(response.getResume().getId())
                            .confirmation(response.getConfirmation())
                    .build());
        });

        return responseDto;
    }
}
