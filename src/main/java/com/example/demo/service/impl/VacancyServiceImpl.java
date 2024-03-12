package com.example.demo.service.impl;

import com.example.demo.dao.VacancyDao;
import com.example.demo.dto.VacancyDto;
import com.example.demo.model.Vacancy;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;

    @Override
    public List<VacancyDto> getAllVacancies() {
        List<Vacancy> vacancies = vacancyDao.getAllVacancies();
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> {
            dtos.add(VacancyDto.builder()
                    .id(e.getId())
                    .name(e.getName())
                    .description(e.getDescription())
                    .expTo(e.getExpTo())
                    .expFrom(e.getExpFrom())
                    .createdDate(e.getCreatedDate())
                    .updateTime(e.getUpdateTime())
                    .authorId(e.getAuthorId())
                    .categoryId(e.getCategoryId())
                    .salary(e.getSalary())
                    .isActive(e.getIsActive())
                    .build());
        });

        return dtos;
    }
}
