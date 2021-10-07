package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.TransportDto;

import java.util.List;

public interface ITransportService {

    List<TransportDto> findAll();

    PageDto<List<TransportDto>> findByPage(Integer page, Integer size);

    TransportDto findById(String id);

    TransportDto save(TransportDto dto);

    TransportDto update(TransportDto dto);

}
