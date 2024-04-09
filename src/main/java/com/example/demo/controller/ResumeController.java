package com.example.demo.controller;

import com.example.demo.dto.EducationInfoDto;
import com.example.demo.dto.ResumeCreateDto;
import com.example.demo.dto.ResumeUpdateDto;
import com.example.demo.dto.WorkExperienceInfoDto;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("resumes")
public class ResumeController {

    private final ResumeService resumeService;
    private final WorkExperienceInfoService workExperienceInfoService;
    private final EducationInfoService educationInfoService;
    private final CategoryService categoryService;
    private final ContactTypeService contactTypeService;

    @GetMapping("active")
    public String getAllResumes(
            Authentication authentication, Model model,
            @RequestParam(name = "page", defaultValue = "0") String page,
            @RequestParam(name = "size", defaultValue = "5") String perPage)
    {
        model.addAttribute("resumes", resumeService.getAllResumes(authentication, page, perPage));
        model.addAttribute("page", Integer.parseInt(page));
        model.addAttribute("perPage", Integer.parseInt(perPage));

        return "resume/all_resumes";
    }

    @GetMapping("{id}")
    public String getResume(Authentication authentication, @PathVariable Long id, Model model) {
        model.addAttribute("resume", resumeService.getResumeById(id, authentication));
        model.addAttribute("work_info", workExperienceInfoService.getWorkInfo(id));
        model.addAttribute("educations", educationInfoService.getEducations(id));

        return "resume/resume";
    }

    @GetMapping("add")
    public String formResume(Model model) {
        model.addAttribute("categories", categoryService.categories());
        return "resume/add_resume";
    }

    @PostMapping("add")
    public String addResume(Authentication authentication, ResumeCreateDto resumeCreateDto, WorkExperienceInfoDto workInfo, EducationInfoDto educ) {
        List<EducationInfoDto> educationInfos = new ArrayList<>();
        educationInfos.add(educ);
        List<WorkExperienceInfoDto> workExperienceInfo = new ArrayList<>();
        workExperienceInfo.add(workInfo);

        resumeCreateDto.setEducationInfo(educationInfos);
        resumeCreateDto.setWorkExperienceInfo(workExperienceInfo);
        resumeService.addResume(resumeCreateDto, authentication);
        return "redirect:/vacancies/active";
    }

    @PostMapping("edit/{id}")
    public String editResume(Authentication authentication, ResumeUpdateDto resumeUpdateDto, @PathVariable Long id) {
        resumeService.editResume(resumeUpdateDto, id, authentication);
        return "redirect:/vacancies/active";
    }

    @GetMapping("edit/{id}")
    public String editResume(Model model, @PathVariable Long id) {
        model.addAttribute("categories", categoryService.categories());
        model.addAttribute("id", id);
        return "resume/edit";
    }

    @GetMapping("add/work_info")
    public String addWorkExp() {
        return "resume/add_work_exp";
    }

    @GetMapping("add/education")
    public String addEducation() {
        return "resume/add_education";
    }

    @GetMapping("add/contact")
    public String addContact(Model model) {
        model.addAttribute("contacts", contactTypeService.getContacts());
        return "resume/add_contacts";
    }

    @PostMapping("update/{id}")
    public String updateResume(@PathVariable Long id) {
        resumeService.updateResume(id);
        return "redirect:/profile";
    }
}
