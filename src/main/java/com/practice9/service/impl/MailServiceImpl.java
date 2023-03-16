package com.practice9.service.impl;

import com.practice9.dto.MailDto;
import com.practice9.entity.Mail;
import com.practice9.entity.MailStatus;
import com.practice9.repository.MailRepository;
import com.practice9.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MailServiceImpl implements MailService {

    private final MailRepository repository;
    private final JavaMailSender mailSender;

    private final String username;

    public MailServiceImpl(MailRepository repository, JavaMailSender mailSender, @Value("${spring.mail.username}") String username) {
        this.repository = repository;
        this.mailSender = mailSender;
        this.username = username;
    }


    @Scheduled(cron = "*/5 * * * *")
    @Override
    public void scheduledSendFailedMessages() {
        List<Mail> failedMails = repository.findAllByStatus(MailStatus.FAILED);
        for (Mail mail : failedMails) {
            sendLetter(mail);
        }
    }


    @Override
    public void sendNewMessage(MailDto dto) {
        Mail mail = new Mail();
        mail.setSubject(dto.getSubject());
        mail.setContent(dto.getContent());
        mail.setReceivers(dto.getReceivers());
        mail.setStatus(MailStatus.NEW);

        mail = repository.save(mail);
        sendLetter(mail);
    }

    @Transactional
    public void sendLetter(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        try {
            message.setFrom(username);
            message.setTo(mail.getReceivers().toArray(String[]::new));
            message.setSubject(mail.getSubject());
            message.setText(mail.getContent());
            mailSender.send(message);
            mail.setStatus(MailStatus.SUCCESSFUL);
            repository.save(mail);
        } catch (MailException e) {
            mail.setStatus(MailStatus.FAILED);
            mail.setErrorMessage(e.getClass().getName() + " " + e.getMessage());
            repository.save(mail);

        }
    }
}
