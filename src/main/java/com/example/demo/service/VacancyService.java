package com.example.demo.service;

import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyDtoForShow;
import com.example.demo.dto.VacancyUpdateDto;
import com.example.demo.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyService {
    List<VacancyDtoForShow> getAllVacancies();

    void deleteVacancyById(Long id, Authentication auth);
    void addVacancy(VacancyDto vacancyDto, Authentication auth);
    void editVacancy(VacancyUpdateDto vacancyDto, long vacancyId, Authentication auth);
    List<VacancyDtoForShow> getVacanciesByCompanyName(String name);
    VacancyDto getVacancyById(Long id);
    void update(Long id);

    Long getAuthorIdByVacancy(Long vacancyId);

    void save(Vacancy vacancy);
}
