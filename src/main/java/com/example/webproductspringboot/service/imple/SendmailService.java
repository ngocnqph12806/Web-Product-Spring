package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.Model.MailModel;
import com.example.webproductspringboot.service.intf.ISendmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@Service
public class SendmailService implements ISendmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    private List<MimeMessage> queue = new ArrayList<>();

    @Override
    public void push(String to, String subject, String message) {
        MailModel mail = MailModel.builder().from("thutamxd@gmail.com").to(to).subject(subject).message(message).build();
        push(mail);
    }

    @Override
    public void pushBcc(List<String> bcc, String subject, String message) {
        MailModel mail = MailModel.builder().from("thutamxd@gmail.com").to("thutamxd@gmail.com").bcc(bcc).subject(subject).message(message).build();
        push(mail);
    }

    public void push(MailModel mail) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(mail.getFrom());
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getMessage(), true);
            helper.setReplyTo(mail.getFrom());
            if (mail.getBcc() != null) {
                for (String x : mail.getBcc()) {
                    helper.addBcc(x);
                }
            }
            queue.add(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void run() {
        while (!queue.isEmpty()) {
            MimeMessage message = queue.get(0);
            javaMailSender.send(message);
            queue.remove(0);
        }
    }
}
