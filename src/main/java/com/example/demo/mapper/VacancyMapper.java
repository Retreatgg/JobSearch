package com.example.demo.mapper;

import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyDtoForShow;
import com.example.demo.dto.VacancyUpdateDto;
import com.example.demo.model.Vacancy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VacancyMapper {

    VacancyDto toDto(Vacancy vacancy);
    VacancyDtoForShow toDtoForShow(Vacancy vacancy);
    VacancyUpdateDto toUpdateDto(Vacancy vacancy);
    Vacancy toModel(VacancyDto vacancyDto);
    List<VacancyDtoForShow> toListVacancyDtoShow(List<Vacancy> vacancies);
}
