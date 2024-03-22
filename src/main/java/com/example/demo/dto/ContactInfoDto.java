package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class ContactInfoDto {
    private Long typeId;
    private String value;
}
