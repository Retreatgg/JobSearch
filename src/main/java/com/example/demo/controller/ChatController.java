package com.example.demo.controller;

import com.example.demo.service.ChatService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;

    @GetMapping("")
    public String openChat(Model model) {
        model.addAttribute("toUser", userService.getUserByEmail("john@example.com"));
        model.addAttribute("fromUser", userService.getUserByEmail("techinnovators@example.com"));
        return "chat/chat";
    }
}
