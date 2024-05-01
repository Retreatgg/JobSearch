package com.example.demo.service.impl;

import com.example.demo.dto.ContactTypeDto;
import com.example.demo.model.ContactType;
import com.example.demo.repository.ContactTypeRepository;
import com.example.demo.service.ContactTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactTypeServiceImpl implements ContactTypeService {

    private final ContactTypeRepository contactTypeRepository;

    @Override
    public List<ContactTypeDto> getContacts() {
        List<ContactTypeDto> contactTypeDtos = new ArrayList<>();
        List<ContactType> contactTypes = contactTypeRepository.findAll();

        contactTypes.forEach(e -> {
            contactTypeDtos.add(ContactTypeDto.builder()
                            .type(e.getType())
                            .id(e.getId())
                    .build());
        });

        return contactTypeDtos;
    }
}
