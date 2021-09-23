package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.InvoiceDto;
import com.example.webproductspringboot.dto.OrderDto;
import com.example.webproductspringboot.dto.TransportDto;
import com.example.webproductspringboot.entity.InvoiceEntity;
import com.example.webproductspringboot.entity.OrderEntity;
import com.example.webproductspringboot.entity.TransportEntity;
import com.example.webproductspringboot.entity.UserEntity;
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
}
