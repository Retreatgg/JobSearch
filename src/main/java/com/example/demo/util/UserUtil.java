//package com.example.demo.util;
//
//import com.example.demo.model.User;
//import com.example.demo.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//public class UserUtil {
//    private final UserRepository userRepository;
//
//    public User getUserByAuth(Authentication auth) {
//        UserDetails userDetails = (UserDetails) auth.getPrincipal();
//        String email = userDetails.getUsername();
//        Optional<User> userOptional = userRepository.findByEmail(email);
//        return userOptional.orElseThrow(() -> new NoSuchElementException("User is not found"));
//    }
//
//    public String getAuthority(Authentication authentication) {
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        return authorities.isEmpty() ? "" : authorities.iterator().next().getAuthority();
//    }
//}
