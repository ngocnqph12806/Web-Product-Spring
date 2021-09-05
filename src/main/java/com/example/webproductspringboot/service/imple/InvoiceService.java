package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.model.dto.InvoiceDto;
import com.example.webproductspringboot.service.intf.IInvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService implements IInvoiceService {
    @Override
    public InvoiceDto saveDto(InvoiceDto invoiceDto) {
        return null;
    }

    @Override
    public InvoiceDto newDto(InvoiceDto invoiceDto) {
        return null;
    }

    @Override
    public InvoiceDto findById(String s) {
        return null;
    }

    @Override
    public List<InvoiceDto> findAll() {
        return null;
    }
}
