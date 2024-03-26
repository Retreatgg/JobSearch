package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dao.VacancyDao;
import com.example.demo.dto.RespondedApplicantsDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyUpdateDto;
import com.example.demo.model.User;
import com.example.demo.model.Vacancy;
import com.example.demo.service.RespondedApplicantService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;
    private final UserDao userDao;
    private final RespondedApplicantService respondedApplicantService;
    private final ResumeService resumeService;

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
    public List<VacancyDto> getActiveVacancy() {
        List<Vacancy> vacancies = vacancyDao.getActiveVacancies();
        return transformationForDtoListVacancies(vacancies);
    }

    @Override
    public void deleteVacancyById(Long id, Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String email = userDetails.getUsername();
        Optional<User> userOptional = userDao.getUserByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getAccountType().equalsIgnoreCase("Employer")) {
                Optional<Vacancy> optionalVacancy = vacancyDao.getVacancyById(id);
                if (optionalVacancy.isPresent()) {
                    Vacancy vacancy = optionalVacancy.get();
                    if (Objects.equals(vacancy.getAuthorId(), user.getId())) {
                        vacancyDao.deleteVacancyById(id);
                    }
                } else {
                    throw new NoSuchElementException("Vacancy with ID " + id + " not found");
                }
            } else {
                throw new NoSuchElementException("User with ID " + user.getId() + " not authorized to delete vacancy with ID " + id);
            }
        }

    }

    @Override
    public void addVacancy(VacancyDto vacancyDto, Authentication auth) {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String authority = authorities.isEmpty() ? "" : authorities.iterator().next().getAuthority();
        if (authority.equalsIgnoreCase("Employer")) {
            Vacancy vacancy = new Vacancy();
            vacancy.setAuthorId(vacancyDto.getAuthorId());
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
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String authority = authorities.isEmpty() ? "" : authorities.iterator().next().getAuthority();
        if (authority.equalsIgnoreCase("Employer")) {
            Optional<Vacancy> vacancyOptional = vacancyDao.getVacancyById(vacancyId);
            if (vacancyOptional.isPresent()) {
                Vacancy vacancy = vacancyOptional.get();
                vacancy.setExpTo(vacancyDto.getExpTo());
                vacancy.setSalary(vacancyDto.getSalary());
                vacancy.setDescription(vacancyDto.getDescription());
                vacancy.setIsActive(vacancyDto.getIsActive());
                vacancy.setName(vacancyDto.getName());
                vacancy.setExpFrom(vacancyDto.getExpFrom());
                vacancy.setCategoryId(vacancyDto.getCategoryId());

                vacancyDao.editVacancy(vacancy);
            } else {
                throw new NoSuchElementException("Not found vacancy with ID " + vacancyId);
            }
        } else {
            throw new NoSuchElementException("User not authorized to edit vacancy");
        }

    }

    @Override
    public List<VacancyDto> getVacanciesByCompanyName(String name) {
        List<Vacancy> vacancies = vacancyDao.getVacanciesByCompanyName(name);
        return transformationForDtoListVacancies(vacancies);
    }

    @Override
    public void respond(Long id, Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String authority = authorities.isEmpty() ? "" : authorities.iterator().next().getAuthority();
        if (authority.equalsIgnoreCase("applicant")) {
            ResumeDto resume = resumeService.getResumeById(id, authentication);
            RespondedApplicantsDto respondedApplicantsDto = RespondedApplicantsDto.builder()
                    .vacancyId(id)
                    .resumeId(resume.getId())
                    .confirmation(false)
                    .build();

            respondedApplicantService.createRespondedApplicant(respondedApplicantsDto);
        }

    }

    private List<VacancyDto> transformationForDtoListVacancies(List<Vacancy> vacancies) {
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(VacancyDto.builder()
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

    public VacancyDto transformationForDtoSingleVacancy(Vacancy vacancy) {
        return VacancyDto.builder()
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .expTo(vacancy.getExpTo())
                .expFrom(vacancy.getExpFrom())
                .authorId(vacancy.getAuthorId())
                .categoryId(vacancy.getCategoryId())
                .salary(vacancy.getSalary())
                .isActive(vacancy.getIsActive())
                .build();

    }

}
