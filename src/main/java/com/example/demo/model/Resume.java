package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Resume {
    private Long id;
    private Long applicantId;
    private String name;
    private Long categoryId;
    private Double salary;
    private boolean isActive;
    private LocalDate createdDate;
    private LocalDateTime updateTime;
}
