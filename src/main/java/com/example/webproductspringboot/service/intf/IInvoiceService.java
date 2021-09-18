package com.example.webproductspringboot.service.intf;


import com.example.webproductspringboot.dto.InvoiceDto;

import java.util.List;

public interface IInvoiceService {

    List<InvoiceDto> findAll();

    InvoiceDto findById(String id);

    InvoiceDto save(InvoiceDto dto);

}
