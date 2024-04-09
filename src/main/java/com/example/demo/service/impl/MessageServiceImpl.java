package com.example.demo.service.impl;

import com.example.demo.dao.MessageDao;
import com.example.demo.dto.MessageDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.service.MessageService;
import com.example.demo.service.RespondedApplicantService;
import com.example.demo.service.ResumeService;
import com.example.demo.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageDao messageDao;
    private final ResumeService resumeService;
    private final FileUtil fileUtil;
    private final RespondedApplicantService respondedApplicantService;

    public List<MessageDto> getMessages(Long respondId) {
        List<Message> messages = messageDao.getMessagesByRespondId(respondId);
        List<MessageDto> messageDtos = new ArrayList<>();

        messages.forEach(e -> {
            messageDtos.add(MessageDto.builder()
                    .content(e.getContent())
                    .timestamp(e.getTimestamp())
                    .build());
        });

        return messageDtos;
    }

    public List<MessageDto> getAllMessages(Authentication authentication) {
        List<MessageDto> messages = new ArrayList<>();

        List<ResumeDto> resumes = resumeService.getResumesByApplicantId(authentication);
        List<Long> listWithId = new ArrayList<>();


       /* resumes.forEach(e -> {
            listWithId.addAll(
                    respondedApplicantService.getRespondIdByResume(e.getId()));
        }); */

        for (var resume : resumes) {
            List<Long> respondId = respondedApplicantService.getRespondIdByResume(resume.getId());
            if (respondId != null) {
                listWithId.addAll(
                        respondedApplicantService.getRespondIdByResume(resume.getId())
                );
            }
        }


        listWithId.forEach(id -> {
            messages.addAll(getMessages(id));
        });

        return messages;
    }
}
