package com.example.demo.service;

import com.example.demo.dao.ChatDao;
import com.example.demo.dto.ChatDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService {

    void saveMessage(ChatDto chatDto);
    List<ChatDto> getChats(String toUser, String fromUser);
}
