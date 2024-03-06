package com.example.demo.model;

import java.time.LocalDate;

public class Vacancy {
    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private Long salary;
    private int expFrom;
    private int expTo;
    private boolean isActive;
    private Long authorId;
    private LocalDate createdDate;
    private LocalDate updateTime;

}
