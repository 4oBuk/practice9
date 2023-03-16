package com.practice9.service.impl;

import com.practice9.entity.Mail;
import com.practice9.entity.MailStatus;
import com.practice9.repository.MailRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class MailServiceImplTest {
    @Mock
    private JavaMailSender mailSender;

    @Mock
    @Autowired
    private MailRepository repository;

    @InjectMocks
    private MailServiceImpl mailService;


    @Test
    void sendLetterSuccessfully() {
        Mail mail = new Mail();
        List<String> receivers = new LinkedList<>();
        receivers.add("example@testings.com");
        mail.setSubject("Sending test");
        mail.setContent("Test email");
        mail.setReceivers(receivers);
        mail.setStatus(MailStatus.NEW);

        doNothing().when(mailSender).send(Mockito.any(SimpleMailMessage.class));
        mail = repository.save(mail);
        mailService.sendLetter(mail);
        assertEquals(MailStatus.SUCCESSFUL, mail.getStatus());
        repository.deleteById(mail.getId());
    }

    @Test
    void sendLetterFailed() {

        Mail mail = new Mail();
        List<String> receivers = new LinkedList<>();
        receivers.add("example@testings.com");
        mail.setSubject("Sending test");
        mail.setContent("Test email");
        mail.setReceivers(receivers);
        mail.setStatus(MailStatus.NEW);

        MailSendException exception = new MailSendException("sending failed");
        doThrow(exception).when(mailSender).send(Mockito.any(SimpleMailMessage.class));
        mail = repository.save(mail);
        mailService.sendLetter(mail);

        assertEquals(mail.getStatus(), MailStatus.FAILED);
        assertEquals(MailSendException.class.getName() + " sending failed", mail.getErrorMessage());
        repository.deleteById(mail.getId());
    }
}