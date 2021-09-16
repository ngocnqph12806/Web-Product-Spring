package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.partner.FormBrandAdminDto;
import com.example.webproductspringboot.dto.partner.IntroBrandAdminDto;

import java.util.List;

public interface IBrandService  {
    List<IntroBrandAdminDto> findAllIntroBrandAdmin();

    IntroBrandAdminDto save(FormBrandAdminDto dto);

    IntroBrandAdminDto findIntroById(String id);

    FormBrandAdminDto findFormById(String id);
}
