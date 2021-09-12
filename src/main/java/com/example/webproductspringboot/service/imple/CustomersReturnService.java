package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.transaction.FormReturnProductDto;
import com.example.webproductspringboot.dto.transaction.IntroReturnProductDto;
import com.example.webproductspringboot.service.intf.ICustomersReturnService;
import org.springframework.stereotype.Service;

@Service
public class CustomersReturnService implements ICustomersReturnService {

    @Override
    public IntroReturnProductDto findIntroById(String id) {
        return null;
    }

    @Override
    public IntroReturnProductDto save(FormReturnProductDto dto) {
        return null;
    }
}
