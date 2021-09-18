package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.TransportDto;
import com.example.webproductspringboot.service.intf.ITransportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService extends AbstractService  implements ITransportService {

    @Override
    public List<TransportDto> findAll() {
        return null;
    }

    @Override
    public TransportDto findById(String id) {
        return null;
    }

    @Override
    public TransportDto save(TransportDto dto) {
        return null;
    }
}
