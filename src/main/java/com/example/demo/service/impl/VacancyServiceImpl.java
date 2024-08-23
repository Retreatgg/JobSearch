package com.example.demo.service.impl;

import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyDtoForShow;
import com.example.demo.dto.VacancyUpdateDto;
import com.example.demo.mapper.VacancyMapper;
import com.example.demo.model.User;
import com.example.demo.model.Vacancy;
import com.example.demo.repository.VacancyRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.RespondedApplicantService;
import com.example.demo.service.VacancyService;
import com.example.demo.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static com.example.demo.enums.AccountType.EMPLOYER;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final RespondedApplicantService respondedApplicantService;
    private final VacancyRepository vacancyRepository;
    private final CategoryService categoryService;
    private final UserUtil userUtil;
    private final VacancyMapper vacancyMapper;


    private final DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");


    @Override
    public List<VacancyDtoForShow> getAllVacancies() {
        List<Vacancy> vacancies = vacancyRepository.findAll();
        return vacancyMapper.toListVacancyDtoShow(vacancies);
    }


    @Override
    public void deleteVacancyById(Long id, Authentication auth) {
        User user = userUtil.getUserByAuth(auth);
        if (user.getAccountType().equals(EMPLOYER.toString())) {
            Vacancy vacancy = findById(id);

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
            Vacancy vacancy = findById(vacancyId);
            if(vacancyDto.getExpTo() != null) {
                vacancy.setExpTo(vacancyDto.getExpTo());
            }
            if(vacancyDto.getExpFrom() != null) {
                vacancy.setExpFrom(vacancy.getExpFrom());
            }
            if(vacancyDto.getDescription() != null) {
                vacancy.setDescription(vacancyDto.getDescription());
            }
            if(vacancyDto.getIsActive() != null) {
                vacancy.setIsActive(vacancyDto.getIsActive());
            }
            if(vacancyDto.getName() != null) {
                vacancy.setName(vacancyDto.getName());
            }
            if(vacancyDto.getCategoryId() != null) {
                vacancy.setCategory(categoryService.findById(vacancyDto.getCategoryId()));
            }
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
                .countResponses(respondedApplicantService.getCountResponsesByVacancyId(e.getId()))
                        .createdDate(e.getCreatedDate().format(formatters))
                        .updateDate(e.getUpdateTime().format(formatters))
                .build()));

        return dtos;
    }

    @Override
    public VacancyDto getVacancyById(Long id) {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow();
        return vacancyMapper.toDto(vacancy);
    }

    @Override
    public void update(Long id) {
        Vacancy vacancy = findById(id);

        vacancy.setUpdateTime(LocalDateTime.now());
        vacancyRepository.save(vacancy);
    }

    @Override
    public Long getAuthorIdByVacancy(Long vacancyId) {
        return vacancyRepository.findById(vacancyId).get().getAuthor().getId();
    }

    @Override
    public void save(Vacancy vacancy) {
        vacancyRepository.save(vacancy);
    }

    private Vacancy findById(Long id) {
        return vacancyRepository.findById(id).orElseThrow();
    }
}
