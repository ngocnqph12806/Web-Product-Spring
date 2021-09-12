package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.partner.FormTransportDto;
import com.example.webproductspringboot.dto.partner.IntroTransprotDto;

public interface ITransportService  {
    IntroTransprotDto findIntroById(String id);

    IntroTransprotDto save(FormTransportDto dto);
}
