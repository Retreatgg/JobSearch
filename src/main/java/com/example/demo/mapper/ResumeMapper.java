package com.example.demo.mapper;

import com.example.demo.dto.ResumeCreateDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.ResumeResponseDto;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResumeMapper {
    @Mapping(source = "applicant.id", target = "applicant")
    ResumeDto toDto(Resume resume);
    Resume toResume(ResumeCreateDto resumeCreateDto);
    List<ResumeDto> toListDto(List<Resume> resumeList);
    ResumeResponseDto toResponseDto(ResumeDto resumeDto);
}
