package com.example.demo.service.impl;

import com.example.demo.dto.RespondedApplicantsDto;
import com.example.demo.model.RespondedApplicant;
import com.example.demo.model.Vacancy;
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

    private final RespondedApplicantsRepository respondedApplicantsRepository;
    private final ResumeRepository resumeRepository;
    private final VacancyRepository vacancyRepository;

    @Override
    public void createRespondedApplicant(RespondedApplicantsDto respondedApplicantsDto) {
        Vacancy vacancy = vacancyRepository.findById(respondedApplicantsDto.getVacancyId()).get();
        RespondedApplicant respondedApplicant = RespondedApplicant.builder()
                .confirmation(respondedApplicantsDto.getConfirmation())
                .resume(resumeRepository.findById(respondedApplicantsDto.getResumeId()).get())
                .vacancy(vacancyRepository.findById(respondedApplicantsDto.getVacancyId()).get())
                .build();

        respondedApplicantsRepository.save(respondedApplicant);
        vacancy.setCountResponses(vacancy.getCountResponses() + 1);
        vacancyRepository.save(vacancy);
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
    public Long getCountResponsesByVacancyId(Long vacancyId) {
        return respondedApplicantsRepository.countRespondedApplicantByVacancyId(vacancyId);
    }

}
