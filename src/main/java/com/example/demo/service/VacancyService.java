package com.example.demo.service;

import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyService {
    List<VacancyDto> getAllVacancies(long userId);
    List<VacancyDto> getVacanciesByCategory(String name, long userId);
    List<VacancyDto> getRespondedVacancies(long userId);
    List<VacancyDto> getVacancyByAuthorId(Long id, long userId);
    List<VacancyDto> getActiveVacancy(long userId);

    void deleteVacancyById(Long id, long userId);
    void addVacancy(VacancyDto vacancyDto, long userId);
    void editVacancy(VacancyUpdateDto vacancyDto, long vacancyId, long userId);
    List<VacancyDto> getVacanciesByCompanyName(String name, long userId);
    VacancyDto getVacancyById(Long id, long userId);
}
