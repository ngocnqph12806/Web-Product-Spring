package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.ReturnDto;

import java.util.List;

public interface ICustomersReturnService {

    List<ReturnDto> findAll();

    ReturnDto findById(String id);

    ReturnDto save(ReturnDto dto);

}
