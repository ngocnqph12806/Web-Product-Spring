package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.ReturnDto;
import com.example.webproductspringboot.service.intf.ICustomersReturnService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersReturnService extends AbstractService  implements ICustomersReturnService {
    @Override
    public List<ReturnDto> findAll() {
        return null;
    }

    @Override
    public ReturnDto findById(String id) {
        return null;
    }

    @Override
    public ReturnDto save(ReturnDto dto) {
        return null;
    }
}
