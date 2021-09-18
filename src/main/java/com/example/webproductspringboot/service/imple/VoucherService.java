package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.VoucherDto;
import com.example.webproductspringboot.service.intf.IVoucherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherService extends AbstractService  implements IVoucherService {


    @Override
    public List<VoucherDto> findAll() {
        return null;
    }

    @Override
    public VoucherDto findById(String id) {
        return null;
    }

    @Override
    public VoucherDto save(VoucherDto dto) {
        return null;
    }

}
