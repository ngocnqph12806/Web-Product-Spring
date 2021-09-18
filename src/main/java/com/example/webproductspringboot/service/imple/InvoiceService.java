package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.InvoiceDto;
import com.example.webproductspringboot.service.intf.IInvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService extends AbstractService  implements IInvoiceService {

    @Override
    public List<InvoiceDto> findAll() {
        return null;
    }

    @Override
    public InvoiceDto findById(String id) {
        return null;
    }

    @Override
    public InvoiceDto save(InvoiceDto dto) {
        return null;
    }
}
