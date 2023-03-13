package com.practice9.controller;

import com.practice9.dto.MailDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("mail")
public class MailController {


    private final String topic;
    private final KafkaOperations<String, MailDto> kafkaOperations;

    public MailController(@Value("${kafka.topic.mail.sent}") String topic, KafkaOperations<String, MailDto> kafkaOperations) {
        this.topic = topic;
        this.kafkaOperations = kafkaOperations;
    }

    @PostMapping("new")
    @ResponseBody
    public String sendMail(@RequestBody MailDto dto) {
        kafkaOperations.send(topic, UUID.randomUUID().toString(), dto);
        return "message sent";
    }

}
