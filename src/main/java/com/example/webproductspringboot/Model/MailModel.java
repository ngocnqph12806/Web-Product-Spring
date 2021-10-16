package com.example.webproductspringboot.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailModel {

    private String from;
    private String to;
    private String subject;
    private String message;
    private List<String> bcc;

}
