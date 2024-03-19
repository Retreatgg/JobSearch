package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dao.VacancyDao;
import com.example.demo.dto.VacancyDto;
import com.example.demo.model.User;
import com.example.demo.model.Vacancy;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;
    private final UserDao userDao;

    @Override
    public List<VacancyDto> getAllVacancies(long applicantId) {
        User user = returnUserById(applicantId);
        assert user != null;
        if (user.getAccountType().equals("Applicant")) {
            List<Vacancy> vacancies = vacancyDao.getAllVacancies();
            return transformationForDtoListVacancies(vacancies);
        }
        return null;
    }

    @Override
    public List<VacancyDto> getVacanciesByCategory(String name, long applicantId) {
        User user = returnUserById(applicantId);
        assert user != null;
        if (user.getAccountType().equals("Applicant")) {
            List<Vacancy> vacancies = vacancyDao.getVacanciesByCategory(name);
            return transformationForDtoListVacancies(vacancies);
        }
        return null;
    }

    @Override
    public List<VacancyDto> getRespondedVacancies(long employeeId) {
        User user = returnUserById(employeeId);
        assert user != null;
        if (user.getAccountType().equals("Employer")) {
            List<Vacancy> vacancies = vacancyDao.getRespondedVacancies();
            return transformationForDtoListVacancies(vacancies);
        }
        return null;
    }

    @Override
    public List<VacancyDto> getVacancyByAuthorId(Long id, long applicantId) {
        User user = returnUserById(applicantId);
        assert user != null;
        if (user.getAccountType().equals("Applicant")) {
            List<Vacancy> vacancies = vacancyDao.getVacancyByAuthorId(id);
            return transformationForDtoListVacancies(vacancies);
        }
        return null;
    }

    @Override
    public List<VacancyDto> getActiveVacancy(long applicantId) {
        User user =  returnUserById(applicantId);
        assert user != null;
        if (user.getAccountType().equals("Applicant")) {
            List<Vacancy> vacancies = vacancyDao.getActiveVacancies();
            return transformationForDtoListVacancies(vacancies);
        }

        return null;
    }

    @Override
    public void deleteVacancyById(Long id, long employeeId) {
        User user = returnUserById(employeeId);
        assert user != null;
        if (user.getAccountType().equals("Employer")) {
            vacancyDao.deleteVacancyById(id);
        }
    }

    @Override
    public void addVacancy(VacancyDto vacancyDto, long employerId) {
        User user = returnUserById(employerId);
        assert user != null;
        if (user.getAccountType().equals("Employer")) {
            Vacancy vacancy = new Vacancy();
            vacancy = editAndAdd(vacancy, vacancyDto);
            vacancyDao.addVacancy(vacancy);
        }
    }

    @Override
    public void editVacancy(VacancyDto vacancyDto, long id, long employerId) {
        User user = returnUserById(employerId);
        assert user != null;
        if (user.getAccountType().equals("Employer")) {
            Vacancy vacancy = new Vacancy();
            vacancy = editAndAdd(vacancy, vacancyDto);
            vacancyDao.editVacancy(vacancy, id);
        }
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

    private User returnUserById(long userId) {
        Optional<User> optionalUser = userDao.getById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user;
        }
        return null;
    }
}
