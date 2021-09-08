package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.entity.StaffEntity;
import com.example.webproductspringboot.dto.InfoStaffDto;
import com.example.webproductspringboot.reponsitory.IStaffReponsitory;
import com.example.webproductspringboot.service.intf.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService implements IStaffService {

    @Autowired
    private IStaffReponsitory _iStaffReponsitory;


    @Override
    public InfoStaffDto getInfo() {
        StaffEntity entity = new StaffEntity();
        entity.setId("123456");
        entity.setUsername("username");
        entity.setAvatar("avatar");
        return InfoStaffDto.toDto(entity);
    }
}
