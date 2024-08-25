package com.example.demo.service;

import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyDtoForShow;
import com.example.demo.dto.VacancyUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyService {
    List<VacancyDtoForShow> getAllVacancies();
    void deleteVacancyById(Long id);
    ResponseEntity<VacancyDtoForShow> addVacancy(VacancyDto vacancyDto);
    ResponseEntity<VacancyDtoForShow> editVacancy(VacancyUpdateDto vacancyDto, long vacancyId);
    List<VacancyDtoForShow> getVacanciesByCompanyName(String name);
    VacancyDtoForShow getVacancyById(Long id);
    ResponseEntity<VacancyDtoForShow> update(Long id);
    Long getAuthorIdByVacancy(Long vacancyId);

}
