package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.ReturnDto;
import com.example.webproductspringboot.entity.CustomersReturnEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.ICustomersReturnReponsitory;
import com.example.webproductspringboot.service.intf.ICustomersReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomersReturnService extends AbstractService implements ICustomersReturnService {

    @Autowired
    private ICustomersReturnReponsitory _iCustomersReturnReponsitory;

    @Override
    public List<ReturnDto> findAll() {
        List<CustomersReturnEntity> lst = _iCustomersReturnReponsitory.findAll();
        Collections.reverse(lst);
        return lst.stream().map(e -> (ReturnDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public ReturnDto findById(String id) {
        Optional<CustomersReturnEntity> optional = _iCustomersReturnReponsitory.findById(id);
        if (optional.isEmpty()) throw new NotFoundException("Hoá đơn khách trả không tồn tại");
        return (ReturnDto) map(optional.get());
    }

    @Override
    public ReturnDto save(ReturnDto dto) {
        CustomersReturnEntity entity = (CustomersReturnEntity) map(dto);
        if(entity == null) throw new BadRequestException("Lỗi dữ liệu");
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(true);
        entity.setCreated(new Date(System.currentTimeMillis()));
        _iCustomersReturnReponsitory.save(entity);
        saveHistory(userEntity, "Thêm hoá đơn khách trả: \n"+entity);
        return (ReturnDto) map(entity);
    }
}
