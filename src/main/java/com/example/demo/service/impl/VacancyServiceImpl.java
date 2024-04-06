package com.example.demo.service.impl;

import com.example.demo.dao.VacancyDao;
import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.model.Vacancy;
import com.example.demo.service.RespondedApplicantService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.VacancyService;
import com.example.demo.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.demo.enums.AccountType.APPLICANT;
import static com.example.demo.enums.AccountType.EMPLOYER;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyDao vacancyDao;
    private final RespondedApplicantService respondedApplicantService;
    private final ResumeService resumeService;

    private final FileUtil fileUtil;

    @Override
    public List<VacancyDtoForShow> getAllVacancies() {
        List<Vacancy> vacancies = vacancyDao.getAllVacancies();
        return transformationForDtoListVacancies(vacancies);
    }

    @Override
    public List<VacancyDtoForShow> getVacanciesByCategory(String name) {
        List<Vacancy> vacancies = vacancyDao.getVacanciesByCategory(name);
        return transformationForDtoListVacancies(vacancies);
    }

    @Override
    public List<VacancyDtoForShow> getActiveVacancy() {
        List<Vacancy> vacancies = vacancyDao.getActiveVacancies();
        return transformationForDtoListVacancies(vacancies);
    }

    @Override
    public void deleteVacancyById(Long id, Authentication auth) {
        User user = fileUtil.getUserByAuth(auth);
        if (user.getAccountType().equals(EMPLOYER.toString())) {
            Vacancy vacancy = getVacancyById(id);

            if (Objects.equals(vacancy.getAuthorId(), user.getId())) {
                vacancyDao.deleteVacancyById(id);
            }
        } else {
            throw new NoSuchElementException("User with ID " + user.getId() + " not authorized to delete vacancy with ID " + id);
        }

    }

    @Override
    public void addVacancy(VacancyDto vacancyDto, Authentication auth) {
        String authority = fileUtil.getAuthority(auth);
        User user = fileUtil.getUserByAuth(auth);

        if (authority.equalsIgnoreCase(EMPLOYER.toString())) {
            Vacancy vacancy = new Vacancy();

            vacancy.setAuthorId(user.getId());
            vacancy.setSalary(vacancyDto.getSalary());
            vacancy.setDescription(vacancyDto.getDescription());
            vacancy.setExpTo(vacancyDto.getExpTo());
            vacancy.setIsActive(vacancyDto.getIsActive());
            vacancy.setCategoryId(vacancyDto.getCategoryId());
            vacancy.setExpFrom(vacancyDto.getExpFrom());
            vacancy.setName(vacancyDto.getName());

            vacancyDao.addVacancy(vacancy);
        }

    }


    @Override
    public void editVacancy(VacancyUpdateDto vacancyDto, long vacancyId, Authentication auth) {
        String authority = fileUtil.getAuthority(auth);

        if (authority.equals(EMPLOYER.toString())) {

            Vacancy vacancy = getVacancyById(vacancyId);
            vacancy.setExpTo(vacancyDto.getExpTo());
            vacancy.setSalary(vacancyDto.getSalary());
            vacancy.setDescription(vacancyDto.getDescription());
            vacancy.setIsActive(vacancyDto.getIsActive());
            vacancy.setName(vacancyDto.getName());
            vacancy.setExpFrom(vacancyDto.getExpFrom());
            vacancy.setCategoryId(vacancyDto.getCategoryId());

            vacancyDao.editVacancy(vacancy);
        } else {
            throw new NoSuchElementException("User not authorized to edit vacancy");
        }

    }

    @Override
    public List<VacancyDtoForShow> getVacanciesByCompanyName(String name) {
        List<Vacancy> vacancies = vacancyDao.getVacanciesByCompanyName(name);
        return transformationForDtoListVacancies(vacancies);
    }

    @Override
    public void respond(Long id, Authentication authentication) {
        String authority = fileUtil.getAuthority(authentication);

        if (authority.equals(APPLICANT.toString())) {
            ResumeDto resume = resumeService.getResumeById(id, authentication);
            RespondedApplicantsDto respondedApplicantsDto = RespondedApplicantsDto.builder()
                    .vacancyId(id)
                    .resumeId(resume.getId())
                    .confirmation(false)
                    .build();

            respondedApplicantService.createRespondedApplicant(respondedApplicantsDto);
        }

    }

    private List<VacancyDtoForShow> transformationForDtoListVacancies(List<Vacancy> vacancies) {
        List<VacancyDtoForShow> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(VacancyDtoForShow.builder()
                        .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .expTo(e.getExpTo())
                .expFrom(e.getExpFrom())
                .authorId(e.getAuthorId())
                .categoryId(e.getCategoryId())
                .salary(e.getSalary())
                .isActive(e.getIsActive())
                .build()));

        return dtos;
    }

    @Override
    public Vacancy getVacancyById(Long id) {
        Optional<Vacancy> vacancyOptional = vacancyDao.getVacancyById(id);
        return vacancyOptional.orElseThrow(() -> new NoSuchElementException("Vacancy by ID: " + id + " is not found"));
    }

}
