package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.model.dto.BannerDto;
import com.example.webproductspringboot.service.intf.IBannerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService implements IBannerService {
    @Override
    public BannerDto saveDto(BannerDto bannerDto) {
        return null;
    }

    @Override
    public BannerDto newDto(BannerDto bannerDto) {
        return null;
    }

    @Override
    public BannerDto findById(String s) {
        return null;
    }

    @Override
    public List<BannerDto> findAll() {
        return null;
    }
}
