package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VacancyDtoForShow {

    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    private Boolean isActive;
    private Long authorId;
    private Long countResponses;

}
