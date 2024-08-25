//package com.example.demo.service.impl;
//
//import com.example.demo.dto.MessageDto;
//import com.example.demo.model.Message;
//import com.example.demo.model.Resume;
//import com.example.demo.model.User;
//import com.example.demo.repository.MessageRepository;
//import com.example.demo.repository.RespondedApplicantsRepository;
//import com.example.demo.repository.ResumeRepository;
//import com.example.demo.service.MessageService;
//import com.example.demo.util.UserUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class MessageServiceImpl implements MessageService {
//
//    private final MessageRepository messageRepository;
//    private final ResumeRepository resumeRepository;
//    private final UserUtil userUtil;
//    private final RespondedApplicantsRepository respondedApplicantsRepository;
//
//    public List<MessageDto> getMessages(Long respondId) {
//        List<Message> messages = messageRepository.findAllByRespondedApplicantsId(respondId);
//        List<MessageDto> messageDtos = new ArrayList<>();
//
//        messages.forEach(e -> {
//            messageDtos.add(MessageDto.builder()
//                    .content(e.getContent())
//                    .timestamp(e.getTimestamp())
//                    .build());
//        });
//
//        return messageDtos;
//    }
//
//    public List<MessageDto> getAllMessages(Authentication authentication) {
//        User user = userUtil.getUserByAuth(authentication);
//        List<MessageDto> messages = new ArrayList<>();
//
//        List<Resume> resumes = resumeRepository.findAllByApplicantId(user.getId());
//        List<Long> listWithId = new ArrayList<>();
//
//        if(resumes != null) {
//
//            for (Resume resume : resumes) {
//                List<Long> respondId = respondedApplicantsRepository.findRespondedApplicantIdByResumeId(resume.getId());
//                if (respondId != null) {
//                    listWithId.addAll(
//                            respondedApplicantsRepository.findRespondedApplicantIdByResumeId(resume.getId())
//                    );
//                }
//            }
//
//
//            listWithId.forEach(id -> {
//                messages.addAll(getMessages(id));
//            });
//
//            return messages;
//        }
//        return null;
//    }
//}
