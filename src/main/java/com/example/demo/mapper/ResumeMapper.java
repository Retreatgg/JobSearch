package com.example.demo.mapper;

import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResumeMapper {

    ResumeDto toDto(Resume resume);
}
