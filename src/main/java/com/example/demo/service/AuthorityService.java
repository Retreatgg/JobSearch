package com.example.demo.service;

import com.example.demo.model.Authority;
import org.springframework.stereotype.Service;

@Service
public interface AuthorityService {

    Authority findById(Long id);
}
