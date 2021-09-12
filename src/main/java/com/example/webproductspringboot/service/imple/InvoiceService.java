package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.transaction.FormInvoiceAdminDto;
import com.example.webproductspringboot.dto.transaction.IntroInvoiceAdminDto;
import com.example.webproductspringboot.service.intf.IInvoiceService;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService implements IInvoiceService {

    @Override
    public IntroInvoiceAdminDto findIntroById(String id) {
        return null;
    }

    @Override
    public IntroInvoiceAdminDto save(FormInvoiceAdminDto dto) {
        return null;
    }
}
