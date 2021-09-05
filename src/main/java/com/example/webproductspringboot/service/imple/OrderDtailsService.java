package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.model.dto.OrderDtailsDto;
import com.example.webproductspringboot.service.intf.IOrderDtailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDtailsService implements IOrderDtailsService {
    @Override
    public OrderDtailsDto saveDto(OrderDtailsDto orderDtailsDto) {
        return null;
    }

    @Override
    public OrderDtailsDto newDto(OrderDtailsDto orderDtailsDto) {
        return null;
    }

    @Override
    public OrderDtailsDto findById(String s) {
        return null;
    }

    @Override
    public List<OrderDtailsDto> findAll() {
        return null;
    }
}
