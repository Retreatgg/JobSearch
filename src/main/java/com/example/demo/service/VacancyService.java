package com.example.demo.service;

import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyDtoForShow;
import com.example.demo.dto.VacancyUpdateDto;
import com.example.demo.model.Vacancy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyService {
    List<VacancyDtoForShow> getAllVacancies();
    List<VacancyDtoForShow> getVacanciesByCategory(String name);
    List<VacancyDtoForShow> getActiveVacancy();

    void deleteVacancyById(Long id, Authentication auth);
    void addVacancy(VacancyDto vacancyDto, Authentication auth);
    void editVacancy(VacancyUpdateDto vacancyDto, long vacancyId, Authentication auth);
    List<VacancyDtoForShow> getVacanciesByCompanyName(String name);
    void respond(Long id, Authentication authentication);

    Vacancy getVacancyById(Long id);
}
