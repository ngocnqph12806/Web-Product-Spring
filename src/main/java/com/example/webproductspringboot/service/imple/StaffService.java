package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.model.dto.StaffDto;
import com.example.webproductspringboot.service.intf.IStaffService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService implements IStaffService {
    @Override
    public StaffDto saveDto(StaffDto staffDto) {
        return null;
    }

    @Override
    public StaffDto newDto(StaffDto staffDto) {
        return null;
    }

    @Override
    public StaffDto findById(String s) {
        return null;
    }

    @Override
    public List<StaffDto> findAll() {
        return null;
    }
}
