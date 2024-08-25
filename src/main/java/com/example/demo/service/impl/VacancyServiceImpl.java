package com.example.demo.service.impl;

import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyDtoForShow;
import com.example.demo.dto.VacancyUpdateDto;
import com.example.demo.mapper.VacancyMapper;
import com.example.demo.model.Vacancy;
import com.example.demo.repository.VacancyRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.RespondedApplicantService;
import com.example.demo.service.UserService;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.demo.enums.AccountType.EMPLOYER;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final RespondedApplicantService respondedApplicantService;
    private final VacancyRepository vacancyRepository;
    private final CategoryService categoryService;
//    private final UserUtil userUtil;
    private final VacancyMapper vacancyMapper;


    private final DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
    private final UserService userService;


    @Override
    public List<VacancyDtoForShow> getAllVacancies() {
        List<Vacancy> vacancies = vacancyRepository.findAll();
        return vacancyMapper.toListVacancyDtoShow(vacancies);
    }

    @Override
    public void deleteVacancyById(Long id) {
//        User user = userUtil.getUserByAuth(auth);
//        if (user.getAccountType().equals(EMPLOYER.toString())) {
//            Vacancy vacancy = findById(id);
//            if (Objects.equals(vacancy.getAuthor().getId(), user.getId())) {
        vacancyRepository.deleteById(id);
//            }
//        } else {
//            throw new NoSuchElementException("User with ID " + user.getId() + " not authorized to delete vacancy with ID " + id);
//        }

    }

    @Override
    public ResponseEntity<VacancyDtoForShow> addVacancy(VacancyDto vacancyDto) {
//        String authority = userUtil.getAuthority(auth);
//        User user = userUtil.getUserByAuth(auth);
//
//        if (authority.equalsIgnoreCase(EMPLOYER.toString())) {
        Vacancy vacancy = vacancyMapper.toModel(vacancyDto);
        vacancy.setCreatedDate(LocalDateTime.now());
        vacancy.setUpdateTime(LocalDateTime.now());
        vacancy.setAuthor(userService.getUserById(1L));
        vacancy.setCountResponses(0L);
        vacancy.setCategory(categoryService.findByName(vacancyDto.getCategoryName()));

        Vacancy addedVacancy = vacancyRepository.save(vacancy);
        return ResponseEntity.ok(vacancyMapper.toDtoForShow(addedVacancy));
//        }
//
//        return (ResponseEntity<VacancyDtoForShow>) ResponseEntity.status(403);
    }


    @Override
    public ResponseEntity<VacancyDtoForShow> editVacancy(VacancyUpdateDto vacancyDto, long vacancyId) {

            Vacancy vacancy = findById(vacancyId);
            if (vacancyDto.getExpTo() != null) {
                vacancy.setExpTo(vacancyDto.getExpTo());
            }
            if (vacancyDto.getExpFrom() != null) {
                vacancy.setExpFrom(vacancy.getExpFrom());
            }
            if (vacancyDto.getDescription() != null) {
                vacancy.setDescription(vacancyDto.getDescription());
            }
            if (vacancyDto.getIsActive() != null) {
                vacancy.setIsActive(vacancyDto.getIsActive());
            }
            if (vacancyDto.getName() != null) {
                vacancy.setName(vacancyDto.getName());
            }
            if (vacancyDto.getCategoryId() != null) {
                vacancy.setCategory(categoryService.findById(vacancyDto.getCategoryId()));
            }
            if (vacancyDto.getSalary() != null) {
                vacancy.setSalary(vacancyDto.getSalary());
            }
            Vacancy editedVacancy = vacancyRepository.save(vacancy);
            return ResponseEntity.ok(vacancyMapper.toDtoForShow(editedVacancy));
    }

    @Override
    public List<VacancyDtoForShow> getVacanciesByCompanyName(String name) {
        List<Vacancy> vacancies = vacancyRepository.findByCompanyName(name);
        return vacancyMapper.toListVacancyDtoShow(vacancies);
    }

    @Override
    public VacancyDtoForShow getVacancyById(Long id) {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow();
        return vacancyMapper.toDtoForShow(vacancy);
    }

    @Override
    public ResponseEntity<VacancyDtoForShow> update(Long id) {
        Vacancy vacancy = findById(id);
        vacancy.setUpdateTime(LocalDateTime.now());
        Vacancy newVacancy = vacancyRepository.save(vacancy);
        return ResponseEntity.ok(vacancyMapper.toDtoForShow(newVacancy));
    }

    @Override
    public Long getAuthorIdByVacancy(Long vacancyId) {
        return vacancyRepository.findById(vacancyId).orElseThrow().getAuthor().getId();
    }

    private Vacancy findById(Long id) {
        return vacancyRepository.findById(id).orElseThrow();
    }
}
