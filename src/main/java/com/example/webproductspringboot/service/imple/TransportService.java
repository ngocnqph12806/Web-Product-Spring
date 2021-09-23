package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.InvoiceDto;
import com.example.webproductspringboot.dto.OrderDto;
import com.example.webproductspringboot.dto.ReviewDto;
import com.example.webproductspringboot.dto.TransportDto;
import com.example.webproductspringboot.entity.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.ITransportReponsitory;
import com.example.webproductspringboot.service.intf.ITransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransportService extends AbstractService implements ITransportService {

    @Autowired
    private ITransportReponsitory _iTransportReponsitory;

    @Override
    public List<TransportDto> findAll() {
        List<TransportEntity> lst = _iTransportReponsitory.findAll();
        Collections.reverse(lst);
        return lst.stream().map(e -> (TransportDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public TransportDto findById(String id) {
        Optional<TransportEntity> optional = _iTransportReponsitory.findById(id);
        if (optional.isEmpty()) throw new NotFoundException("Hoá đơn vận chuyển không tồn tại");
        return (TransportDto) map(optional.get());
    }

    @Override
    public TransportDto save(TransportDto dto) {
        TransportEntity entity = (TransportEntity) map(dto);
        if (entity == null) throw new BadRequestException("Lỗi dữ liệu");
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(true);
        entity.setCreated(new Date(System.currentTimeMillis()));
        _iTransportReponsitory.save(entity);
        saveHistory(userEntity, "Thêm hoá đơn vận chuyển : \n" + entity);
        return (TransportDto) map(entity);
    }

    @Override
    public TransportDto update(TransportDto dto) {
        TransportEntity entity = (TransportEntity) map(dto);
        if (entity == null) throw new BadRequestException("Lỗi dữ liệu");
        UserEntity userEntity = getUserLogin();
        Optional<TransportEntity> optional = _iTransportReponsitory.findById(entity.getId());
        if (optional.isEmpty()) throw new NotFoundException("Hoá đơn vận chuyển không tồn tại");
        TransportEntity fake = optional.get();
        if (entity.getStatus() == null) entity.setStatus(fake.getStatus());
        entity.setCreated(fake.getCreated());
        _iTransportReponsitory.save(entity);
        saveHistory(userEntity, "Sửa hoá đơn vận chuyển: \n" + fake + "\n" + entity);
        return (TransportDto) map(entity);
    }
}
