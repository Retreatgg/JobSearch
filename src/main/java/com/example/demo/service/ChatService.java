package com.example.demo.service;

import com.example.demo.dao.ChatDao;
import com.example.demo.dto.ChatDto;
import com.example.demo.dto.SendMessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService {

    void saveMessage(SendMessageDto sendDto);
    List<ChatDto> getChats(String toUser, String fromUser);
}
