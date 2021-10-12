package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.ReturnDto;
import com.example.webproductspringboot.vo.ReturnDetailDto;

import java.util.List;

public interface ICustomersReturnService {

    List<ReturnDto> findAll();

    PageDto<List<ReturnDto>> findByPage(Integer page, Integer size);

    ReturnDto findById(String id);

    ReturnDto save(ReturnDto dto);

    ReturnDto update(ReturnDto dto);

    ReturnDetailDto saveReturnDetail(ReturnDetailDto x);

    void removeReturn(ReturnDto returnDtoSave);

    void removeReturnDetailByIdReturn(String idOrder);

    void removeReturnDetailById(String id);
}
