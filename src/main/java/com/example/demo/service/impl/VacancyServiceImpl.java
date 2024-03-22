package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dao.VacancyDao;
import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyUpdateDto;
import com.example.demo.model.User;
import com.example.demo.model.Vacancy;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;
    private final UserDao userDao;

    @Override
    public List<VacancyDto> getAllVacancies(long applicantId) {
        User user = returnUserById(applicantId);
        if (user != null && user.getAccountType().equals("Applicant")) {
            List<Vacancy> vacancies = vacancyDao.getAllVacancies();
            return transformationForDtoListVacancies(vacancies);
        }
        return null;
    }

    @Override
    public List<VacancyDto> getVacanciesByCategory(String name, long applicantId) {
        User user = returnUserById(applicantId);
        if (user != null && user.getAccountType().equals("Applicant")) {
            List<Vacancy> vacancies = vacancyDao.getVacanciesByCategory(name);
            return transformationForDtoListVacancies(vacancies);
        }
        return null;
    }

    @Override
    public List<VacancyDto> getRespondedVacancies(long employeeId) {
        User user = returnUserById(employeeId);
        if (user != null && user.getAccountType().equals("Employer")) {
            List<Vacancy> vacancies = vacancyDao.getRespondedVacancies();
            return transformationForDtoListVacancies(vacancies);
        }
        return null;
    }

    @Override
    public List<VacancyDto> getVacancyByAuthorId(Long id, long applicantId) {
        User user = returnUserById(applicantId);
        if (user != null && user.getAccountType().equals("Applicant")) {
            List<Vacancy> vacancies = vacancyDao.getVacancyByAuthorId(id);
            return transformationForDtoListVacancies(vacancies);
        }
        return null;
    }

    @Override
    public List<VacancyDto> getActiveVacancy(long applicantId) {
        User user = returnUserById(applicantId);
        if (user != null && user.getAccountType().equals("Applicant")) {
            List<Vacancy> vacancies = vacancyDao.getActiveVacancies();
            return transformationForDtoListVacancies(vacancies);
        }

        return null;
    }

    @Override
    public void deleteVacancyById(Long id, long employeeId) {
        User user = returnUserById(employeeId);
        if (user != null && user.getAccountType().equals("Employer")) {
            Optional<Vacancy> optionalVacancy = vacancyDao.getVacancyById(id);
            if (optionalVacancy.isPresent()) {
                Vacancy vacancy = optionalVacancy.get();
                if (vacancy.getAuthorId() == employeeId) {
                    vacancyDao.deleteVacancyById(id);
                }
            } else {
                throw new NoSuchElementException("Vacancy with ID " + id + " not found");
            }
        } else {
            throw new NoSuchElementException("User with ID " + employeeId + " not authorized to delete vacancy with ID " + id);
        }

    }

    @Override
    public void addVacancy(VacancyDto vacancyDto, long employerId) {
        User user = returnUserById(employerId);
        assert user != null;
        if (user.getAccountType().equals("Employer")) {
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
        } else {
            throw new NoSuchElementException("User with ID " + employerId + " not authorized to add vacancy");
        }
    }

    @Override
    public void editVacancy(VacancyUpdateDto vacancyDto, long vacancyId, long employerId) {
        User user = returnUserById(employerId);
        if (user != null && user.getAccountType().equals("Employer")) {
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
            throw new NoSuchElementException("User with ID " + employerId + " not authorized to edit vacancy");
        }
    }

    @Override
    public List<VacancyDto> getVacanciesByCompanyName(String name, long applicantId) {
        User user = returnUserById(applicantId);
        if (user != null && user.getAccountType().equals("Applicant")) {
            List<Vacancy> vacancies = vacancyDao.getVacanciesByCompanyName(name);
            return transformationForDtoListVacancies(vacancies);
        }
        return null;
    }

    @Override
    public VacancyDto getVacancyById(Long id, long applicantId) {
        User user = returnUserById(applicantId);
        if (user != null && user.getAccountType().equals("Applicant")) {
            Vacancy vacancy = vacancyDao.getVacancyById(id).orElseThrow(() -> new NoSuchElementException("Can not find Vacancy by ID:" + id));
            return transformationForDtoSingleVacancy(vacancy);
        }

        return null;
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

    private User returnUserById(long userId) {
        Optional<User> optionalUser = userDao.getById(userId);
        return optionalUser.orElse(null);
    }
}
