package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.partner.FormVoucherDto;
import com.example.webproductspringboot.dto.partner.IntroVoucherDto;
import com.example.webproductspringboot.service.intf.IVoucherService;
import org.springframework.stereotype.Service;

@Service
public class VoucherService implements IVoucherService {

    @Override
    public IntroVoucherDto findIntroById(String id) {
        return null;
    }

    @Override
    public IntroVoucherDto save(FormVoucherDto dto) {
        return null;
    }
}
