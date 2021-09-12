package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.partner.FormBrandAdminDto;
import com.example.webproductspringboot.dto.partner.IntroBrandAdminDto;
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

    @Override
    public IntroBrandAdminDto save(FormBrandAdminDto dto) {
        return null;
    }

    @Override
    public IntroBrandAdminDto findIntroById(String id) {
        return null;
    }
}
