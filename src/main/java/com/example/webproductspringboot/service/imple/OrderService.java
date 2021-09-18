package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.OrderDto;
import com.example.webproductspringboot.service.intf.IOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends AbstractService  implements IOrderService {
    @Override
    public List<OrderDto> findAll() {
        return null;
    }

    @Override
    public OrderDto findById(String id) {
        return null;
    }

    @Override
    public OrderDto save(OrderDto dto) {
        return null;
    }
}
