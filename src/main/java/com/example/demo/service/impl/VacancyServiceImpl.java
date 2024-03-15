package com.example.demo.service.impl;

import com.example.demo.dao.VacancyDao;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.VacancyDto;
import com.example.demo.model.Resume;
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
        return transformationForDtoListVacancies(vacancies);
    }

    @Override
    public List<VacancyDto> getVacanciesByCategory(String name) {
        List<Vacancy> vacancies = vacancyDao.getVacanciesByCategory(name);
        return transformationForDtoListVacancies(vacancies);
    }

    @Override
    public List<VacancyDto> getRespondedVacancies() {
        List<Vacancy> vacancies = vacancyDao.getRespondedVacancies();
        return transformationForDtoListVacancies(vacancies);
    }

    @Override
    public List<VacancyDto> getVacancyByAuthorId(Long id) {
        List<Vacancy> vacancies = vacancyDao.getVacancyByAuthorId(id);
        return transformationForDtoListVacancies(vacancies);
    }

    @Override
    public List<VacancyDto> getActiveVacancy() {
        List<Vacancy> vacancies = vacancyDao.getActiveVacancies();
        return transformationForDtoListVacancies(vacancies);
    }

    @Override
    public void deleteVacancyById(Long id) {
        vacancyDao.deleteVacancyById(id);
    }

    @Override
    public void addVacancy(VacancyDto vacancyDto) {
        Vacancy vacancy = new Vacancy();
        vacancy = editAndAdd(vacancy, vacancyDto);
        vacancyDao.addVacancy(vacancy);
    }

    @Override
    public void editVacancy(VacancyDto vacancyDto, long id) {
        Vacancy vacancy = new Vacancy();
        vacancy = editAndAdd(vacancy, vacancyDto);
        vacancyDao.editVacancy(vacancy, id);
    }

    private Vacancy editAndAdd(Vacancy vacancy, VacancyDto vacancyDto) {
        vacancy.setId(vacancyDto.getId());
        vacancy.setName(vacancyDto.getName());
        vacancy.setSalary(vacancyDto.getSalary());
        vacancy.setIsActive(vacancyDto.getIsActive());
        vacancy.setCreatedDate(vacancyDto.getCreatedDate());
        vacancy.setUpdateTime(vacancyDto.getUpdateTime());
        vacancy.setAuthorId(vacancyDto.getAuthorId());
        vacancy.setCategoryId(vacancyDto.getCategoryId());
        vacancy.setDescription(vacancyDto.getDescription());
        vacancy.setExpFrom(vacancyDto.getExpFrom());
        vacancy.setExpTo(vacancyDto.getExpTo());

        return vacancy;
    }

    private List<VacancyDto> transformationForDtoListVacancies(List<Vacancy> vacancies) {
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
