package com.example.demo.service.impl;

import com.example.demo.dao.ContactsInfoDao;
import com.example.demo.dto.ContactInfoDto;
import com.example.demo.model.ContactInfo;
import com.example.demo.service.ContactInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService {
    private final ContactsInfoDao contactsInfoDao;
    @Override
    public void createContactInfo(Long id, ContactInfoDto contactInfoDto) {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setResumeId(id);
        contactInfo.setValue(contactInfoDto.getValue());
        contactInfo.setTypeId(contactInfoDto.getTypeId());

        contactsInfoDao.createContactsInfo(contactInfo);
    }

    @Override
    public void delete(long id) {
        contactsInfoDao.deleteContactInfo(id);
    }
}
