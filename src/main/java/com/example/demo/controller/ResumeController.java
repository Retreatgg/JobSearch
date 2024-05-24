package com.example.demo.controller;

import com.example.demo.dto.ResumeCreateDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.ResumeUpdateDto;
import com.example.demo.dto.VacancyDto;
import com.example.demo.model.User;
import com.example.demo.service.*;
import com.example.demo.util.UserUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("resumes")
public class ResumeController {

    private final ResumeService resumeService;
    private final WorkExperienceInfoService workExperienceInfoService;
    private final EducationInfoService educationInfoService;
    private final RespondedApplicantService respondedApplicantService;
    private final CategoryService categoryService;
    private final ContactTypeService contactTypeService;
    private final UserUtil userUtil;

    @GetMapping("active")
    public String getAllResumes(
            Authentication authentication, Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable)
    {
        Page<ResumeDto> page = resumeService.getAllResumes(authentication, pageable);

        model.addAttribute("page", page);
        model.addAttribute("pageNumber", pageable.getPageNumber());
        model.addAttribute("pageSize", pageable.getPageSize());
        model.addAttribute("auth", authentication);

        return "resume/all_resumes";
    }

    @GetMapping("{id}")
    public String getResume(Authentication authentication, @PathVariable Long id, Model model) {
        model.addAttribute("resume", resumeService.getResumeById(id, authentication));
        model.addAttribute("work_info", workExperienceInfoService.getWorkInfo(id));
        model.addAttribute("educations", educationInfoService.getEducations(id));
        model.addAttribute("auth", authentication);

        return "resume/resume";
    }

    @GetMapping("add")
    public String formResume(Model model) {
        model.addAttribute("categories", categoryService.categories());
        model.addAttribute("contacts", contactTypeService.getContacts());
        return "resume/add_resume";
    }

    @PostMapping("add")
    public String addResume(Authentication authentication, @Valid ResumeCreateDto resumeCreateDto) {
        System.out.println(authentication);
        resumeService.addResume(resumeCreateDto, authentication);
        return "redirect:/profile";
    }

    @PostMapping("edit/{id}")
    public String editResume(Authentication authentication, @Valid ResumeUpdateDto resumeUpdateDto, @PathVariable Long id) {
        resumeService.editResume(resumeUpdateDto, id, authentication);
        return "redirect:/profile";
    }

    @GetMapping("edit/{id}")
    public String editResume(Model model, @PathVariable Long id) {
        model.addAttribute("categories", categoryService.categories());
        model.addAttribute("id", id);
        model.addAttribute("resume", resumeService.findById(id));
        return "resume/edit";
    }

    @PostMapping("update/{id}")
    public String updateResume(@PathVariable Long id) {
        resumeService.updateResume(id);
        return "redirect:/profile";
    }

    @GetMapping("responses/{id}")
    public String getResponsesByVacancyId(Authentication auth, Model model, @PathVariable Long id) {
        User user = userUtil.getUserByAuth(auth);
        Long authorResumeId = resumeService.getAuthorIdByResume(id);
        if(user.getId() == authorResumeId) {
            List<VacancyDto> vacancies = respondedApplicantService.findVacancyByRusumeId(id);
            model.addAttribute("vacancies", vacancies);
            return "profile/responses";
        } else {
            return "errors/error";
        }
    }


}
