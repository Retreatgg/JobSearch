package com.example.demo.service.impl;

import com.example.demo.dto.ContactInfoDto;
import com.example.demo.model.ContactInfo;
import com.example.demo.repository.ContactTypeRepository;
import com.example.demo.repository.ContactsInfoRepository;
import com.example.demo.repository.ResumeRepository;
import com.example.demo.service.ContactInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService {

    private final ContactsInfoRepository contactsInfoRepository;
    private final ResumeRepository resumeRepository;
    private final ContactTypeRepository contactTypeRepository;

    @Override
    public void createContactInfo(Long id, ContactInfoDto contactInfoDto) {
        ContactInfo contactInfo = ContactInfo.builder()
                .resume(resumeRepository.findById(id).get())
                .contactValue(contactInfoDto.getValue())
                .type(contactTypeRepository.findByType(contactInfoDto.getType()).get())
                .build();

        contactsInfoRepository.save(contactInfo);
    }

    @Override
    public void delete(long id) {
        contactsInfoRepository.deleteById(id);
    }
}
