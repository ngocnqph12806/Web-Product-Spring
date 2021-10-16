package com.example.webproductspringboot.service.intf;

import java.util.List;

public interface ISendmailService {

    void push(String to, String subject, String message);

    void pushBcc(List<String> bcc, String subject, String message);

}
