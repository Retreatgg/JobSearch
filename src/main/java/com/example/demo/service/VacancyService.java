package com.example.demo.service;

import com.example.demo.dto.VacancyDto;
import com.example.demo.model.Vacancy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyService {
    List<VacancyDto> getAllVacancies();
}
