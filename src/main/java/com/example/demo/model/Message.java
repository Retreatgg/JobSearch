package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Message {
    private Long id;
    private Long respondedApplicantsId;
    private String content;
    private LocalDateTime timestamp;
}
