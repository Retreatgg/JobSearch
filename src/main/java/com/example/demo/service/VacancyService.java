package com.example.demo.service;

import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.VacancyDto;
import com.example.demo.model.User;
import com.example.demo.model.Vacancy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyService {
    List<VacancyDto> getAllVacancies(long userId);
    List<VacancyDto> getVacanciesByCategory(String name, long userId);
    List<VacancyDto> getRespondedVacancies(long userId);
    List<VacancyDto> getVacancyByAuthorId(Long id, long uesrId);
    List<VacancyDto> getActiveVacancy(long userId);

    void deleteVacancyById(Long id, long userId);
    void addVacancy(VacancyDto vacancyDto, long userId);
    void editVacancy(VacancyDto vacancyDto, long id, long userId);
}
