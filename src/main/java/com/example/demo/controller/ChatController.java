package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;
    private final FileUtil fileUtil;


    @GetMapping("open")
    public String openChat(Model model, @RequestParam(name = "toUser") Long toUserId, Authentication authentication) {

        User user = fileUtil.getUserByAuth(authentication);
        String toUserEmail = userService.getUserEmailById(toUserId);
        model.addAttribute("fromUser", userService.getUserByEmail(user.getEmail()));
        model.addAttribute("toUser", userService.getUserByEmail(toUserEmail));

        model.addAttribute("senderMessage", chatService.getChats(toUserEmail, user.getEmail()));
        model.addAttribute("recipientMessage", chatService.getChats(user.getEmail(), toUserEmail));
        return "chat/chat";
    }

    @PostMapping("send")
    public String sendMessage(Authentication authentication, SendMessageDto message,
                              @RequestParam(name = "toUser") Long toUserId) {
        chatService.saveMessage(authentication, message);
        return "redirect:/chat/open?toUser=" + toUserId;
    }
}
