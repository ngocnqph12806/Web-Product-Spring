package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.TransportDto;

import java.util.List;

public interface ITransportService {

    List<TransportDto> findAll();

    TransportDto findById(String id);

    TransportDto save(TransportDto dto);

}
