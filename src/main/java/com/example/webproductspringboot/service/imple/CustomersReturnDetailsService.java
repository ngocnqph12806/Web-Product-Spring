package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.model.dto.CustomersReturnDetailsDto;
import com.example.webproductspringboot.service.intf.ICustomersReturnDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersReturnDetailsService implements ICustomersReturnDetailsService {
    @Override
    public CustomersReturnDetailsDto saveDto(CustomersReturnDetailsDto customersReturnDetailsDto) {
        return null;
    }

    @Override
    public CustomersReturnDetailsDto newDto(CustomersReturnDetailsDto customersReturnDetailsDto) {
        return null;
    }

    @Override
    public CustomersReturnDetailsDto findById(String s) {
        return null;
    }

    @Override
    public List<CustomersReturnDetailsDto> findAll() {
        return null;
    }
}
