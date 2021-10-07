package com.example.webproductspringboot.service.intf;


import com.example.webproductspringboot.dto.InvoiceDto;
import com.example.webproductspringboot.dto.PageDto;

import java.util.List;

public interface IInvoiceService {

    List<InvoiceDto> findAll();

    PageDto<List<InvoiceDto>> findByPage(Integer page, Integer size);

    InvoiceDto findById(String id);

    InvoiceDto save(InvoiceDto dto);

    InvoiceDto update(InvoiceDto dto);

}
