package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.transaction.FormOrderAdminDto;
import com.example.webproductspringboot.dto.transaction.IntroOrderAdminDto;
import com.example.webproductspringboot.service.intf.IOrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    @Override
    public IntroOrderAdminDto findIntroById(String id) {
        return null;
    }

    @Override
    public IntroOrderAdminDto saveOrder(FormOrderAdminDto dto) {
        return new IntroOrderAdminDto();
    }
}
