package com.example.demo.service.impl;

import com.example.demo.model.Authority;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public Authority findById(Long id) {
        return authorityRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Authority is not found"));
    }
}
