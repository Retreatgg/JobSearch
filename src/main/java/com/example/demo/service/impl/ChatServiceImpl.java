package com.example.demo.service.impl;

import com.example.demo.dao.ChatDao;
import com.example.demo.dto.ChatDto;
import com.example.demo.model.Chat;
import com.example.demo.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatDao chatDao;

    @Override
    public void saveMessage(ChatDto chatDto) {
        Chat chat = new Chat();

        chat.setFromUserEmail(chatDto.getFromUserEmail());
        chat.setMessage(chatDto.getMessage());
        chat.setSendTime(LocalDateTime.now());
        chat.setToUserEmail(chatDto.getToUserEmail());

        chatDao.saveMessage(chat);
    }
}
