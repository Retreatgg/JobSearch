package com.example.demo.controller;

import com.example.demo.dto.ChatDto;
import com.example.demo.dto.SendMessageDto;
import com.example.demo.model.User;
import com.example.demo.service.ChatService;
import com.example.demo.service.UserService;
import com.example.demo.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;
    private final FileUtil fileUtil;

    @GetMapping("")
    public String openChat(Model model) {
        model.addAttribute("toUser", userService.getUserByEmail("john@example.com"));
        model.addAttribute("fromUser", userService.getUserByEmail("techinnovators@example.com"));
        model.addAttribute("senderMessage", chatService.getChats("techinnovators@example.com", "john@example.com"));
        model.addAttribute("recipientMessage", chatService.getChats("john@example.com", "techinnovators@example.com"));
        return "chat/chat";
    }

    @PostMapping("send")
    public String sendMessage(Authentication authentication, SendMessageDto message) {
        chatService.saveMessage(authentication, message);
        return "redirect:/chat";
    }
}
