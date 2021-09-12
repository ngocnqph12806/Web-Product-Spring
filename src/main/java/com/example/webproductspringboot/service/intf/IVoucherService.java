package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.partner.FormVoucherDto;
import com.example.webproductspringboot.dto.partner.IntroVoucherDto;

public interface IVoucherService {
    IntroVoucherDto findIntroById(String id);

    IntroVoucherDto save(FormVoucherDto dto);
}
