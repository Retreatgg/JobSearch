package com.example.demo.service;

import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyUpdateDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyService {
    List<VacancyDto> getAllVacancies(Authentication auth);
    List<VacancyDto> getVacanciesByCategory(String name, Authentication auth);
    List<VacancyDto> getRespondedVacancies(Authentication auth);
    List<VacancyDto> getVacancyByAuthorId(Long id, Authentication auth);
    List<VacancyDto> getActiveVacancy(Authentication auth);

    void deleteVacancyById(Long id, Authentication auth);
    void addVacancy(VacancyDto vacancyDto, Authentication auth);
    void editVacancy(VacancyUpdateDto vacancyDto, long vacancyId, Authentication auth);
    List<VacancyDto> getVacanciesByCompanyName(String name, Authentication auth);
}
