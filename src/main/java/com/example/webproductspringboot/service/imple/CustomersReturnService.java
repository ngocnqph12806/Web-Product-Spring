package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.OrderDto;
import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.ReturnDto;
import com.example.webproductspringboot.entity.CustomersReturnEntity;
import com.example.webproductspringboot.entity.OrderEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.ICustomersReturnReponsitory;
import com.example.webproductspringboot.service.intf.ICustomersReturnService;
import com.example.webproductspringboot.utils.CookieUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomersReturnService extends AbstractService implements ICustomersReturnService {

    private final ICustomersReturnReponsitory _iCustomersReturnReponsitory;

    protected CustomersReturnService(HttpServletRequest request, ICustomersReturnReponsitory iCustomersReturnReponsitory) {
        super(request);
        _iCustomersReturnReponsitory = iCustomersReturnReponsitory;
    }

    @Override
    public List<ReturnDto> findAll() {
        List<CustomersReturnEntity> lst = _iCustomersReturnReponsitory.findAll();
        Collections.reverse(lst);
        return lst.stream().map(e -> (ReturnDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public PageDto<List<ReturnDto>> findByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, sortAZ("created"));
        Page<CustomersReturnEntity> entities = _iCustomersReturnReponsitory.findAll(pageable);
        List<ReturnDto> lst = entities.getContent().stream().map(e -> (ReturnDto) map(e)).collect(Collectors.toList());
        return new PageDto<>(entities.getTotalPages(), entities.getTotalPages(), lst);
    }

    @Override
    public ReturnDto findById(String id) {
        Optional<CustomersReturnEntity> optional = _iCustomersReturnReponsitory.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "return", "return.not.found"));
        return (ReturnDto) map(optional.get());
    }

    @Override
    public ReturnDto save(ReturnDto dto) {
        CustomersReturnEntity entity = (CustomersReturnEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(true);
        entity.setCreated(new Date(System.currentTimeMillis()));
        entity.setIdStaff(userEntity);
        _iCustomersReturnReponsitory.save(entity);
        saveHistory(userEntity, "Thêm hoá đơn khách trả", entity.toString());
        return (ReturnDto) map(entity);
    }

    @Override
    public ReturnDto update(ReturnDto dto) {
        CustomersReturnEntity entity = (CustomersReturnEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        Optional<CustomersReturnEntity> optional = _iCustomersReturnReponsitory.findById(entity.getId());
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "return", "return.not.found"));
        CustomersReturnEntity fake = optional.get();
        if (entity.getStatus() == null) entity.setStatus(fake.getStatus());
        entity.setCreated(fake.getCreated());
        entity.setIdStaff(fake.getIdStaff());
        _iCustomersReturnReponsitory.save(entity);
        saveHistory(userEntity, "Sửa hoá đơn khach trả", fake + "\n" + entity);
        return (ReturnDto) map(entity);
    }
}
