package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.InvoiceDto;
import com.example.webproductspringboot.dto.OrderDto;
import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.entity.InvoiceEntity;
import com.example.webproductspringboot.entity.OrderEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IOrderReponsitory;
import com.example.webproductspringboot.service.intf.IOrderService;
import com.example.webproductspringboot.utils.CookieUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService extends AbstractService implements IOrderService {

    private final IOrderReponsitory _iOrderReponsitory;

    protected OrderService(HttpServletRequest request, IOrderReponsitory iOrderReponsitory) {
        super(request);
        _iOrderReponsitory = iOrderReponsitory;
    }

    @Override
    public List<OrderDto> findAll() {
        List<OrderEntity> lst = _iOrderReponsitory.findAll();
        Collections.reverse(lst);
        return lst.stream().map(e -> (OrderDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public PageDto<List<OrderDto>> findByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, sortAZ("created"));
        Page<OrderEntity> entities = _iOrderReponsitory.findAll(pageable);
        List<OrderDto> lst = entities.getContent().stream().map(e -> (OrderDto) map(e)).collect(Collectors.toList());
        return new PageDto<>(entities.getTotalPages(), entities.getTotalPages(), lst);
    }

    @Override
    public OrderDto findById(String id) {
        Optional<OrderEntity> optional = _iOrderReponsitory.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "order", "order.not.found"));
        return (OrderDto) map(optional.get());
    }

    @Override
    public OrderDto save(OrderDto dto) {
        OrderEntity entity = (OrderEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(true);
        entity.setCreated(new Date(System.currentTimeMillis()));
        entity.setStaffCreate(userEntity);
        _iOrderReponsitory.save(entity);
        saveHistory(userEntity, "Thêm hoá đơn đặt hàng", entity.toString());
        return (OrderDto) map(entity);
    }

    @Override
    public OrderDto update(OrderDto dto) {
        OrderEntity entity = (OrderEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        Optional<OrderEntity> optional = _iOrderReponsitory.findById(entity.getId());
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "order", "order.not.found"));
        OrderEntity fake = optional.get();
        if (entity.getStatus() == null) entity.setStatus(fake.getStatus());
        entity.setCreated(fake.getCreated());
        entity.setStaffCreate(fake.getStaffCreate());
        _iOrderReponsitory.save(entity);
        saveHistory(userEntity, "Sửa hoá đơn đặt hàng", fake + "\n" + entity);
        return (OrderDto) map(entity);
    }
}
