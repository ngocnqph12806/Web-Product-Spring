package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.model.dto.CustomersReturnDto;
import com.example.webproductspringboot.service.intf.ICustomersReturnService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersReturnService implements ICustomersReturnService {
    @Override
    public CustomersReturnDto saveDto(CustomersReturnDto customersReturnDto) {
        return null;
    }

    @Override
    public CustomersReturnDto newDto(CustomersReturnDto customersReturnDto) {
        return null;
    }

    @Override
    public CustomersReturnDto findById(String s) {
        return null;
    }

    @Override
    public List<CustomersReturnDto> findAll() {
        return null;
    }
}
