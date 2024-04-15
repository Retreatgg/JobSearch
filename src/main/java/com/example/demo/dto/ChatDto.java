package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChatDto {

    private String fromUserEmail;
    private String toFromUser;
    private LocalDateTime sendTime;
    private Long respondId;
}
