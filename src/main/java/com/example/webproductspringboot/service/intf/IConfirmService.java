package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.UserDto;

public interface IConfirmService {

    void push(Integer code, UserDto dto, String type);

    Boolean put(Integer code, String type);

}
