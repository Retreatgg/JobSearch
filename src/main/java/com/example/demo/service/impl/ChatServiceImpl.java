//package com.example.demo.service.impl;
//
//import com.example.demo.dto.ChatDto;
//import com.example.demo.dto.SendMessageDto;
//import com.example.demo.model.Chat;
//import com.example.demo.model.User;
//import com.example.demo.repository.ChatRepository;
//import com.example.demo.service.ChatService;
//import com.example.demo.util.UserUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class ChatServiceImpl implements ChatService {
//
//    private final UserUtil userUtil;
//    private final ChatRepository chatRepository;
//
//    @Override
//    public void saveMessage(Authentication authentication, SendMessageDto sendDto) {
//        User user = userUtil.getUserByAuth(authentication);
//        Chat chat = Chat.builder()
//                .fromUserEmail(user.getEmail())
//                .toUserEmail(sendDto.getToUserEmail())
//                .message(sendDto.getMessage())
//                .sendTime(LocalDateTime.now())
//                .build();
//
//        chatRepository.save(chat);
//    }
//
//    @Override
//    public List<ChatDto> getChats(String toUser, String fromUser) {
//        List<Chat> chats = chatRepository.findByToUserAndFromUser(toUser, fromUser);
//        List<ChatDto> chatList = new ArrayList<>();
//
//        chats.forEach(chat -> {
//            chatList.add(
//                    ChatDto.builder()
//                            .fromUserEmail(chat.getFromUserEmail())
//                            .toUserEmail(chat.getToUserEmail())
//                            .message(chat.getMessage())
//                            .sendTime(chat.getSendTime())
//                            .build());
//        });
//
//        return chatList;
//    }
//}
