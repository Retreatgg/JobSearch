package com.example.demo.service.impl;

import com.example.demo.dao.ContactsInfoDao;
import com.example.demo.dao.ContactsTypeDao;
import com.example.demo.dto.ContactInfoDto;
import com.example.demo.model.ContactInfo;
import com.example.demo.service.ContactInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService {

    private final ContactsInfoDao contactsInfoDao;
    private final ContactsTypeDao contactsTypeDao;

    @Override
    public void createContactInfo(Long id, ContactInfoDto contactInfoDto) {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setResumeId(id);
        contactInfo.setValue(contactInfoDto.getValue());
        Long typeId = contactsTypeDao.getContactId(contactInfoDto.getType());
        contactInfo.setTypeId(typeId);

        contactsInfoDao.createContactsInfo(contactInfo);
    }

    @Override
    public void delete(long id) {
        contactsInfoDao.deleteContactInfo(id);
    }
}
