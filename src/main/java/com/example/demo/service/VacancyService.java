package com.example.demo.service;

import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyDtoForShow;
import com.example.demo.dto.VacancyUpdateDto;
import com.example.demo.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyService {
    List<VacancyDtoForShow> getAllVacancies();
    void deleteVacancyById(Long id, Authentication auth);
    ResponseEntity<VacancyDtoForShow> addVacancy(VacancyDto vacancyDto, Authentication auth);
    ResponseEntity<VacancyDtoForShow> editVacancy(VacancyUpdateDto vacancyDto, long vacancyId, Authentication auth);
    List<VacancyDtoForShow> getVacanciesByCompanyName(String name);
    VacancyDtoForShow getVacancyById(Long id);
    ResponseEntity<VacancyDtoForShow> update(Long id);
    Long getAuthorIdByVacancy(Long vacancyId);

}
