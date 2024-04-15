package com.example.demo.service.impl;

import com.example.demo.dao.ChatDao;
import com.example.demo.dto.ChatDto;
import com.example.demo.dto.SendMessageDto;
import com.example.demo.model.Chat;
import com.example.demo.model.User;
import com.example.demo.service.ChatService;
import com.example.demo.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatDao chatDao;
    private final FileUtil fileUtil;

    @Override
    public void saveMessage(Authentication authentication, SendMessageDto sendDto) {
        User user = fileUtil.getUserByAuth(authentication);
        Chat chat = new Chat();

        chat.setFromUserEmail(user.getEmail());
        chat.setMessage(sendDto.getMessage());
        chat.setSendTime(LocalDateTime.now());
        chat.setToUserEmail(sendDto.getToUserEmail());

        chatDao.saveMessage(chat);
    }

    @Override
    public List<ChatDto> getChats(String toUser, String fromUser) {
        List<Chat> chats = chatDao.getChats(toUser, fromUser);
        List<ChatDto> chatList = new ArrayList<>();

        chats.forEach(chat -> {
            chatList.add(
                    ChatDto.builder()
                            .fromUserEmail(chat.getFromUserEmail())
                            .toUserEmail(chat.getToUserEmail())
                            .message(chat.getMessage())
                            .sendTime(chat.getSendTime())
                            .build());
        });

        return chatList;
    }
}
