package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.VoucherDto;

import java.util.List;

public interface IVoucherService {

    List<VoucherDto> findAll();

    VoucherDto findById(String id);

    VoucherDto save(VoucherDto dto);

}
