package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactInfo {
    private Long id;
    private Long typeId;
    private Long resumeId;
    private String value;
}
