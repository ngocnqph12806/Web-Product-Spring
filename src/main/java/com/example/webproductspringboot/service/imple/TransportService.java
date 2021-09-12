package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.partner.FormTransportDto;
import com.example.webproductspringboot.dto.partner.IntroTransprotDto;
import com.example.webproductspringboot.service.intf.ITransportService;
import org.springframework.stereotype.Service;

@Service
public class TransportService implements ITransportService {

    @Override
    public IntroTransprotDto findIntroById(String id) {
        return null;
    }

    @Override
    public IntroTransprotDto save(FormTransportDto dto) {
        return null;
    }
}
