package com.example.demo.controller;

import com.example.demo.dto.ChatDto;
import com.example.demo.dto.SendMessageDto;
import com.example.demo.model.User;
import com.example.demo.service.ChatService;
import com.example.demo.service.UserService;
import com.example.demo.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;
    private final UserUtil userUtil;


    @GetMapping("open")
    public String openChat(Model model, @RequestParam(name = "toUser") Long toUserId, Authentication authentication) {

        User user = userUtil.getUserByAuth(authentication);
        String toUserEmail = userService.getUserEmailById(toUserId);
        model.addAttribute("fromUser", userService.getUserByEmail(user.getEmail()));
        model.addAttribute("toUser", userService.getUserByEmail(toUserEmail));

        List<ChatDto> allMessages = new ArrayList<>();
        allMessages.addAll(chatService.getChats(toUserEmail, user.getEmail()));
        allMessages.addAll(chatService.getChats(user.getEmail(), toUserEmail));

        model.addAttribute("allMessages", allMessages);
        return "chat/chat";
    }

    @PostMapping("send")
    public String sendMessage(Authentication authentication, SendMessageDto message,
                              @RequestParam(name = "toUser") Long toUserId) {
        chatService.saveMessage(authentication, message);
        return "redirect:/chat/open?toUser=" + toUserId;
    }
}
