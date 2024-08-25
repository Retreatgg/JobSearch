package com.example.demo.mapper;

import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyDtoForShow;
import com.example.demo.dto.VacancyUpdateDto;
import com.example.demo.model.Vacancy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VacancyMapper {

    VacancyDto toDto(Vacancy vacancy);
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "category.id", target = "categoryId")
    VacancyDtoForShow toDtoForShow(Vacancy vacancy);
    VacancyUpdateDto toUpdateDto(Vacancy vacancy);
    @Mapping(target = "author", ignore = true)
    Vacancy toModel(VacancyDto vacancyDto);
    List<VacancyDtoForShow> toListVacancyDtoShow(List<Vacancy> vacancies);
}
