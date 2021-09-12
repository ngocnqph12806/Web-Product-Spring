package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.product.IntroBrandAdminDto;
import com.example.webproductspringboot.reponsitory.IBrandReponsitory;
import com.example.webproductspringboot.service.intf.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService implements IBrandService {

    @Autowired
    private IBrandReponsitory _iBrandReponsitory;

    @Override
    public List<IntroBrandAdminDto> findAllIntroBrandAdmin() {
        return _iBrandReponsitory.findAll().stream().map(IntroBrandAdminDto::toDto).collect(Collectors.toList());
    }
}
