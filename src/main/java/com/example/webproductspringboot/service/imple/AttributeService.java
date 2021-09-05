package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.reponsitory.IAttributeReponsitory;
import com.example.webproductspringboot.service.intf.IAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AttributeService implements IAttributeService {

    @Autowired
    private IAttributeReponsitory _iAttributeReponsitory;

}
