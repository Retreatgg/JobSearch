package com.example.demo.service.impl;

import com.example.demo.model.Authority;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

          var user = userRepository.findByEmail(username)
                  .orElseThrow(() -> new UsernameNotFoundException("User is not found"));

          return new User(user.getEmail(), user.getPassword(), getAuthorities(user.getAuthorities()));
    }


    private Collection<? extends GrantedAuthority> getAuthorities(List<Authority> authorityList) {
        return authorityList.stream()
                .map(e -> new SimpleGrantedAuthority(e.getAuthority()))
                .toList();
    }
}
