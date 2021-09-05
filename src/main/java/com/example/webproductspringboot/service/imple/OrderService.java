package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.model.dto.OrderDto;
import com.example.webproductspringboot.service.intf.IOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Override
    public OrderDto saveDto(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto newDto(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto findById(String s) {
        return null;
    }

    @Override
    public List<OrderDto> findAll() {
        return null;
    }
}
