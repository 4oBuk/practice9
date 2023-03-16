package com.practice9.repository;

import com.practice9.entity.Mail;
import com.practice9.entity.MailStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MailRepository extends CrudRepository<Mail, String> {

    List<Mail> findAllByStatus(MailStatus status);
}
