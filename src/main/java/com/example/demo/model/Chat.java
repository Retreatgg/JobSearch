package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Chat {

    private Long id;
    private String fromUserEmail;
    private String toUserEmail;
    private String message;
    private LocalDateTime sendTime;
}
