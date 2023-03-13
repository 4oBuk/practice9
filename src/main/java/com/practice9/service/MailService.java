package com.practice9.service;

import com.practice9.dto.MailDto;
import com.practice9.entity.Mail;

public interface MailService {
    void scheduledSendFailedMessages();

    void sendNewMessage(MailDto dto);
}
