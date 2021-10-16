package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.Model.ConfirmModel;
import com.example.webproductspringboot.Model.MailModel;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.service.intf.IConfirmService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ConfirmService implements IConfirmService {

    private static List<ConfirmModel<UserDto>> lst = new ArrayList<>();

    @Override
    public void push(Integer code, UserDto dto, String type) {
        ConfirmModel<UserDto> model = new ConfirmModel<UserDto>(code, dto, new Date(), type);
        lst.add(model);
    }

    @Override
    public Boolean put(Integer code, String type) {
        for (ConfirmModel<UserDto> x : lst) {
            if (x.getType().equals(type) && Objects.equals(x.getCode(), code)) {
                return true;
            }
        }
        return false;
    }


    @Scheduled(fixedRate = 10, initialDelay = 5000)
    public void run() {
        for (ConfirmModel<UserDto> x : lst) {
            long seconds = (new Date().getTime() - x.getTime().getTime())/1000;
            System.out.println(seconds);
            if (seconds > 60) {
                lst.remove(x);
                break;
            }
        }
    }

}
