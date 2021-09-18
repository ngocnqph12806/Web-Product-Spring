package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.BannerDto;

import java.util.List;

public interface IBannerService {

    List<BannerDto> findAll();

    BannerDto findById(String id);

    BannerDto save(BannerDto dto);

    BannerDto update(BannerDto dto);

}
