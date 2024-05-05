package com.example.demo.service.impl;

import com.example.demo.dao.VacancyDao;
import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.model.Vacancy;
import com.example.demo.repository.VacancyRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.VacancyService;
import com.example.demo.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static com.example.demo.enums.AccountType.EMPLOYER;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyDao vacancyDao;
    private final VacancyRepository vacancyRepository;
    private final CategoryService categoryService;

    private final UserUtil userUtil;


    @Override
    public Page<Vacancy> getAllVacancies(Pageable pageable) {
        Page<Vacancy> vacancies = vacancyRepository.findAll(pageable);
        return vacancies;
    }

    @Override
    public List<VacancyDtoForShow> getVacanciesByCategory(String name) {
        List<Vacancy> vacancies = vacancyRepository.findByCategoryName(name);
        return transformationForDtoListVacancies(vacancies);
    }

    @Override
    public List<VacancyDtoForShow> getActiveVacancy(String page, String perPage, long categoryId) {
        int numberPage = Integer.parseInt(page);
        int perPageNumber = Integer.parseInt(perPage);
        int offset = numberPage * perPageNumber;

        List<Vacancy> vacancies = vacancyDao.getActiveVacancies(perPageNumber, offset);

        if(categoryId != 0) {
            List<Vacancy> vacanciesByCategory = vacancies.stream()
                    .filter(e -> e.getCategory().getId() == categoryId)
                    .toList();

            return transformationForDtoListVacancies(vacanciesByCategory);
        }

        return transformationForDtoListVacancies(vacancies);
    }

    @Override
    public void deleteVacancyById(Long id, Authentication auth) {
        User user = userUtil.getUserByAuth(auth);
        if (user.getAccountType().equals(EMPLOYER.toString())) {
            Vacancy vacancy = getVacancyById(id);

            if (Objects.equals(vacancy.getAuthor().getId(), user.getId())) {
                vacancyRepository.deleteById(id);
            }
        } else {
            throw new NoSuchElementException("User with ID " + user.getId() + " not authorized to delete vacancy with ID " + id);
        }

    }

    @Override
    public void addVacancy(VacancyDto vacancyDto, Authentication auth) {
        String authority = userUtil.getAuthority(auth);
        User user = userUtil.getUserByAuth(auth);

        if (authority.equalsIgnoreCase(EMPLOYER.toString())) {
            Vacancy vacancy = Vacancy.builder()
                    .salary(vacancyDto.getSalary())
                    .createdDate(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .name(vacancyDto.getName())
                    .expTo(vacancyDto.getExpTo())
                    .expFrom(vacancyDto.getExpFrom())
                    .isActive(vacancyDto.getIsActive())
                    .description(vacancyDto.getDescription())
                    .author(user)
                    .category(categoryService.findByName(vacancyDto.getCategoryName()))
                    .build();

            vacancyRepository.save(vacancy);
        }

    }


    @Override
    public void editVacancy(VacancyUpdateDto vacancyDto, long vacancyId, Authentication auth) {
        String authority = userUtil.getAuthority(auth);

        if (authority.equals(EMPLOYER.toString())) {

            Vacancy vacancy = getVacancyById(vacancyId);
            vacancy.setExpTo(vacancyDto.getExpTo());
            vacancy.setSalary(vacancyDto.getSalary());
            vacancy.setDescription(vacancyDto.getDescription());
            vacancy.setIsActive(vacancyDto.getIsActive());
            vacancy.setName(vacancyDto.getName());
            vacancy.setExpFrom(vacancyDto.getExpFrom());
            vacancy.setCategory(categoryService.findById(vacancyDto.getCategoryId()));

           vacancyRepository.save(vacancy);
        } else {
            throw new NoSuchElementException("User not authorized to edit vacancy");
        }

    }

    @Override
    public List<VacancyDtoForShow> getVacanciesByCompanyName(String name) {
        List<Vacancy> vacancies = vacancyRepository.findByCompanyName(name);
        return transformationForDtoListVacancies(vacancies);
    }

    private List<VacancyDtoForShow> transformationForDtoListVacancies(List<Vacancy> vacancies) {
        List<VacancyDtoForShow> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(VacancyDtoForShow.builder()
                        .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .expTo(e.getExpTo())
                .expFrom(e.getExpFrom())
                .authorId(e.getAuthor().getId())
                .categoryId(e.getCategory().getId())
                .salary(e.getSalary())
                .isActive(e.getIsActive())
                .build()));

        return dtos;
    }

    @Override
    public Vacancy getVacancyById(Long id) {
        Optional<Vacancy> vacancyOptional = vacancyRepository.findById(id);
        return vacancyOptional.orElseThrow(() -> new NoSuchElementException("Vacancy by ID: " + id + " is not found"));
    }

    @Override
    public void update(Long id) {
        Vacancy vacancy = getVacancyById(id);

        vacancy.setUpdateTime(LocalDateTime.now());
       vacancyRepository.save(vacancy);
    }

    @Override
    public Long getAuthorIdByVacancy(Long vacancyId) {
        return vacancyRepository.findById(vacancyId).get().getAuthor().getId();
    }

}
