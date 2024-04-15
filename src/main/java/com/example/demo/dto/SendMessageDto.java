package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendMessageDto {
    private String toUserEmail;
    private String message;
}
