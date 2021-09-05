package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.model.dto.AttributeDto;
import com.example.webproductspringboot.reponsitory.IAttributeReponsitory;
import com.example.webproductspringboot.service.intf.IAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeService implements IAttributeService {

    @Autowired
    private IAttributeReponsitory _iAttributeReponsitory;

    @Override
    public AttributeDto saveDto(AttributeDto attributeDto) {
        return null;
    }

    @Override
    public AttributeDto newDto(AttributeDto attributeDto) {
        return null;
    }

    @Override
    public AttributeDto findById(String s) {
        return null;
    }

    @Override
    public List<AttributeDto> findAll() {
        return null;
    }
}
