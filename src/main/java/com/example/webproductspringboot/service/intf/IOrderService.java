package com.example.webproductspringboot.service.intf;


import com.example.webproductspringboot.dto.OrderDto;

import java.util.List;

public interface IOrderService {

    List<OrderDto> findAll();

    OrderDto findById(String id);

    OrderDto save(OrderDto dto);

}
