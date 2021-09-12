package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.transaction.FormOrderAdminDto;
import com.example.webproductspringboot.dto.transaction.IntroOrderAdminDto;

public interface IOrderService  {
    IntroOrderAdminDto findIntroById(String id);

    IntroOrderAdminDto saveOrder(FormOrderAdminDto dto);
}
