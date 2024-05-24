package com.example.demo.controller;

import com.example.demo.dto.UserCreateDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;

import java.io.UnsupportedEncodingException;

@Controller
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final UserService userService;

    @GetMapping("login")
    public String login() {
        return "login/login";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("userDto", new UserCreateDto());
        return "/user/register";
    }

    @PostMapping("register")
    public String register(@Valid UserCreateDto userDto, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "/user/register";
        }
        userService.createUser(userDto, request);

        return "redirect:/profile";
    }

    @GetMapping("forgot_password")
    public String showForgotPasswordForm() {
        return "user/forgot_password";
    }

    @GetMapping("reset_password")
    public String showResetPassword(@RequestParam(name = "token") String token,
                                    Model model) {
        try {
            userService.getByResetPasswordToken(token);
            model.addAttribute("token", token);
        } catch (UsernameNotFoundException e) {
            model.addAttribute("error", "Invalid token");
        }
        return "user/reset_password";
    }


    @PostMapping("forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        try {
            userService.makeResetPasswdLink(request);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
        } catch (UsernameNotFoundException | UnsupportedEncodingException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (MessagingException ex) {
            model.addAttribute("error", "Error while sending email");
        }
        return "user/forgot_password";
    }

    @PostMapping("reset_password")
    public String resetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        try {
            User user = userService.getByResetPasswordToken(token);
            userService.updatePassword(user, password);
            model.addAttribute("message", "You have successfully changed your password.");
        } catch (UsernameNotFoundException e) {
            model.addAttribute("message", "Invalid token");
        }

        return "user/message";
    }
}
