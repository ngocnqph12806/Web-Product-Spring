package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.transaction.FormInvoiceAdminDto;
import com.example.webproductspringboot.dto.transaction.IntroInvoiceAdminDto;

public interface IInvoiceService  {
    IntroInvoiceAdminDto findIntroById(String id);

    IntroInvoiceAdminDto save(FormInvoiceAdminDto dto);
}
