package com.example.webproductspringboot.service.intf;


import com.example.webproductspringboot.dto.ChechoutDto;
import com.example.webproductspringboot.dto.OrderDetailDto;
import com.example.webproductspringboot.dto.OrderDto;
import com.example.webproductspringboot.dto.PageDto;

import java.util.List;

public interface IOrderService {

    List<OrderDto> findAll();

    PageDto<List<OrderDto>> findByPage(Integer page, Integer size);

    OrderDto findById(String id);

    OrderDto save(OrderDto dto);

    OrderDto update(OrderDto dto);

    void saveDetailOrder(OrderDetailDto x);

    void removeOrder(OrderDto dtoSave);

    void removeDetailOrderById(String id);

    OrderDetailDto findOrderDetailById(String idOrderDetail);

    OrderDto saveCheckout(ChechoutDto dto);

    List<OrderDto> getAllOrderByUserLogin(String id);

    void updatePayment(String id);

    List<Long> findAllDoanhSoTrongNam();

    List<Long> findAllDoanhThuTrongNam();
}
