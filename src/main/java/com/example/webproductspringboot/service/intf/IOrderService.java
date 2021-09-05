package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.entity.OrderEntity;
import com.example.webproductspringboot.model.dto.OrderDto;
import com.example.webproductspringboot.service.IGenericService;

public interface IOrderService extends IGenericService<OrderDto, String> {
}
