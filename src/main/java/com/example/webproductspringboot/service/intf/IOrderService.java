package com.example.webproductspringboot.service.intf;


import com.example.webproductspringboot.dto.OrderDto;
import com.example.webproductspringboot.dto.PageDto;

import java.util.List;

public interface IOrderService {

    List<OrderDto> findAll();

    PageDto<List<OrderDto>> findByPage(Integer page, Integer size);

    OrderDto findById(String id);

    OrderDto save(OrderDto dto);

    OrderDto update(OrderDto dto);

}
