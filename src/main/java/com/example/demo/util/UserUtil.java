package com.example.demo.util;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUtil {
    private final UserDao userDao;

    public User getUserByAuth(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String email = userDetails.getUsername();
        Optional<User> userOptional = userDao.getUserByEmail(email);
        return userOptional.orElseThrow(() -> new NoSuchElementException("User is not found"));
    }

    public String getAuthority(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.isEmpty() ? "" : authorities.iterator().next().getAuthority();
    }
}
