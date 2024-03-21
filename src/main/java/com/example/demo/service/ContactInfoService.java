package com.example.demo.service;

import com.example.demo.dto.ContactInfoDto;

public interface ContactInfoService {
    void createContactInfo(Long id, ContactInfoDto contactInfoDto);
}
