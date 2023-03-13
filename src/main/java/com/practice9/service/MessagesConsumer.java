package com.practice9.service;

import com.practice9.dto.MailDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class MessagesConsumer {

    @KafkaListener(topics = "${kafka.topic.mail.sent}")
    public void readMessage(MailDto dto) {
        System.out.println(dto);
    }
//read message from kafka and call service to update email
}
