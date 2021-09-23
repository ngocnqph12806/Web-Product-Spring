package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.InvoiceDto;
import com.example.webproductspringboot.dto.OrderDto;
import com.example.webproductspringboot.entity.InvoiceEntity;
import com.example.webproductspringboot.entity.OrderEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IOrderReponsitory;
import com.example.webproductspringboot.service.intf.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService extends AbstractService implements IOrderService {

    @Autowired
    private IOrderReponsitory _iOrderReponsitory;

    @Override
    public List<OrderDto> findAll() {
        List<OrderEntity> lst = _iOrderReponsitory.findAll();
        Collections.reverse(lst);
        return lst.stream().map(e -> (OrderDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(String id) {
        Optional<OrderEntity> optional = _iOrderReponsitory.findById(id);
        if (optional.isEmpty()) throw new NotFoundException("Hoá đơn đặt hàng không tồn tại");
        return (OrderDto) map(optional.get());
    }

    @Override
    public OrderDto save(OrderDto dto) {
        OrderEntity entity = (OrderEntity) map(dto);
        if (entity == null) throw new BadRequestException("Lỗi dữ liệu");
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(true);
        entity.setCreated(new Date(System.currentTimeMillis()));
        entity.setStaffCreate(userEntity);
        _iOrderReponsitory.save(entity);
        saveHistory(userEntity, "Thêm hoá đơn đặt hàng: \n" + entity);
        return (OrderDto) map(entity);
    }
}
