package com.example.demo.service;

import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyUpdateDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyService {
    List<VacancyDto> getAllVacancies();
    List<VacancyDto> getVacanciesByCategory(String name);
    List<VacancyDto> getRespondedVacancies();
    List<VacancyDto> getActiveVacancy();

    void deleteVacancyById(Long id, Authentication auth);
    void addVacancy(VacancyDto vacancyDto, Authentication auth);
    void editVacancy(VacancyUpdateDto vacancyDto, long vacancyId, Authentication auth);
    List<VacancyDto> getVacanciesByCompanyName(String name);
}
