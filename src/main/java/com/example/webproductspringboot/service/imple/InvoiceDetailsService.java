package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.model.dto.InvoiceDetailsDto;
import com.example.webproductspringboot.service.intf.IInvoiceDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceDetailsService implements IInvoiceDetailsService {
    @Override
    public InvoiceDetailsDto saveDto(InvoiceDetailsDto invoiceDetailsDto) {
        return null;
    }

    @Override
    public InvoiceDetailsDto newDto(InvoiceDetailsDto invoiceDetailsDto) {
        return null;
    }

    @Override
    public InvoiceDetailsDto findById(String s) {
        return null;
    }

    @Override
    public List<InvoiceDetailsDto> findAll() {
        return null;
    }
}
