package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.BannerDto;
import com.example.webproductspringboot.dto.BrandDto;
import com.example.webproductspringboot.entity.BannerEntity;
import com.example.webproductspringboot.entity.BrandEntity;
import com.example.webproductspringboot.service.intf.IBannerService;
import org.springframework.stereotype.Service;

@Service
public class BannerService implements IBannerService {

    private Object toObj(Object data) {
        if (data == null) return null;
        if (data instanceof BannerDto) {
            BannerDto dto = (BannerDto) data;
            return BannerEntity.builder()
                    .build();
        } else if (data instanceof BrandEntity) {
            BannerEntity entity = (BannerEntity) data;
            return BannerDto.builder()
                    .build();
        }
        return null;
    }
}
