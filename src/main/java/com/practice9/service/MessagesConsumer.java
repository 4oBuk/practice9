package com.practice9.service;

import com.practice9.dto.MailDto;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class MessagesConsumer {


    private MailService mailService;
    @KafkaListener(topics = "${kafka.topic.mail.sent}")
    public void readMessage(MailDto dto) {
        mailService.sendNewMessage(dto);
    }
}
