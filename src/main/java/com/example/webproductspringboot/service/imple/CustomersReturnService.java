package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.ReturnDto;
import com.example.webproductspringboot.entity.CustomersReturnDetailsEntity;
import com.example.webproductspringboot.entity.CustomersReturnEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.ICustomersReturnDetailsReponsitory;
import com.example.webproductspringboot.reponsitory.ICustomersReturnReponsitory;
import com.example.webproductspringboot.service.intf.ICustomersReturnService;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.ReturnDetailDto;
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
    private final ICustomersReturnDetailsReponsitory _iCustomersReturnDetailsReponsitory;

    protected CustomersReturnService(HttpServletRequest request, ICustomersReturnReponsitory iCustomersReturnReponsitory, ICustomersReturnDetailsReponsitory iCustomersReturnDetailsReponsitory) {
        super(request);
        _iCustomersReturnReponsitory = iCustomersReturnReponsitory;
        _iCustomersReturnDetailsReponsitory = iCustomersReturnDetailsReponsitory;
    }

    @Override
    public List<ReturnDto> findAll() {
        List<CustomersReturnEntity> lst = _iCustomersReturnReponsitory.findAll(sortAZ("created"));
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
        entity.setStatus(false);
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
        entity.setIdStaff(fake.getIdStaff());
        entity.setCreated(fake.getCreated());
        _iCustomersReturnReponsitory.save(entity);
        saveHistory(userEntity, "Sửa hoá đơn khach trả", fake + "\n" + entity);
        return (ReturnDto) map(entity);
    }

    @Override
    public ReturnDetailDto saveReturnDetail(ReturnDetailDto x) {
        CustomersReturnDetailsEntity entity = (CustomersReturnDetailsEntity) map(x);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        _iCustomersReturnDetailsReponsitory.save(entity);
        saveHistory(userEntity, "Thêm chi tiết hoá đơn khách trả", entity.toString());
        return (ReturnDetailDto) map(entity);
    }

    @Override
    public void removeReturn(ReturnDto returnDtoSave) {
        _iCustomersReturnReponsitory.deleteById(returnDtoSave.getId());
    }

    @Override
    public void removeReturnDetailByIdReturn(String idReturn) {
        _iCustomersReturnDetailsReponsitory.deleteByIdOrder(idReturn);
    }

    @Override
    public void removeReturnDetailById(String id) {
        _iCustomersReturnDetailsReponsitory.deleteById(id);
    }
}
