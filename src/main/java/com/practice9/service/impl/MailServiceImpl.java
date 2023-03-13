package com.practice9.service.impl;

import com.practice9.dto.MailDto;
import com.practice9.entity.Mail;
import com.practice9.entity.MailStatus;
import com.practice9.repository.EmailRepository;
import com.practice9.service.MailService;
import org.springframework.kafka.core.KafkaOperations;

public class MailServiceImpl implements MailService {

    private EmailRepository repository;

    @Override
    public void scheduledSendFailedMessages() {

    }


    @Override
    public void sendNewMessage(MailDto dto) {
        Mail mail = new Mail();
        mail.setSubject(dto.getSubject());
        mail.setContent(dto.getContent());
        mail.setReceivers(dto.getReceivers());
        mail.setStatus(MailStatus.NEW);

        mail = repository.save(mail);

    }
}
