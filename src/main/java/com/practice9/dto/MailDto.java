package com.practice9.dto;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MailDto {
    private String subject;
    private String content;
    private List<String> receivers;

}
