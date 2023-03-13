package com.practice9.repository;

import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.practice9.entity.Mail;
import com.practice9.entity.MailStatus;
import lombok.AllArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.elasticsearch.client.elc.QueryBuilders.matchQuery;

public interface EmailRepository extends CrudRepository<Mail, String> {

    List<Mail> findAllByStatus(MailStatus status);
//
//    private ElasticsearchOperations operations;
//
//    public List<Mail> findAllUnsendMails() {
//
//        NativeQuery query = new NativeQueryBuilder().withQuery(matchQuery("status", MailStatus.NEW.name())).build();
//    }
//
//    public Mail save(Mail mail) {
//        return operations.save(mail);
//    }
}
