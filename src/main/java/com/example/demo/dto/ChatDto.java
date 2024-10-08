package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChatDto {

    private String fromUserEmail;
    private String toUserEmail;
    private String message;
    private LocalDateTime sendTime;
}
