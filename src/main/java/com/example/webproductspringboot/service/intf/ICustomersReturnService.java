package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.transaction.FormReturnProductDto;
import com.example.webproductspringboot.dto.transaction.IntroReturnProductDto;

public interface ICustomersReturnService  {
    IntroReturnProductDto findIntroById(String id);

    IntroReturnProductDto save(FormReturnProductDto dto);
}
