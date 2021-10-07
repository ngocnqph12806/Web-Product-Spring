package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.VoucherDto;
import com.example.webproductspringboot.vo.VoucherApplyVo;

import java.util.List;

public interface IVoucherService {

    List<VoucherDto> findAll();

    PageDto<List<VoucherDto>> findByPage(Integer page, Integer size);

    VoucherDto findById(String id);

    VoucherDto save(VoucherDto dto);

    VoucherDto update(VoucherDto dto);

    VoucherApplyVo findByCode(String code);

}
