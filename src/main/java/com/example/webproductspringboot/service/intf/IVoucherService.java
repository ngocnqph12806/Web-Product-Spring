package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.VoucherDto;

public interface IVoucherService {

    VoucherDto findIntroById(String id);

    VoucherDto save(VoucherDto dto);

}
