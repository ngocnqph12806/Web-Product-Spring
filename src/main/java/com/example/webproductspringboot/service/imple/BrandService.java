package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.partner.FormBrandAdminDto;
import com.example.webproductspringboot.dto.partner.IntroBrandAdminDto;
import com.example.webproductspringboot.entity.BrandEntity;
import com.example.webproductspringboot.exception.InternalServerException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IBrandReponsitory;
import com.example.webproductspringboot.service.intf.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
        BrandEntity brandEntity = dto.toEntity();
        if (brandEntity.getId() == null || brandEntity.getId().isBlank() || brandEntity.getId().isEmpty()) {
            brandEntity = brandEntity.toBuilder()
                    .id(UUID.randomUUID().toString())
                    .status(true)
                    .created(new Date(System.currentTimeMillis()))
                    .build();
        } else {
            Optional<BrandEntity> entity = _iBrandReponsitory.findById(brandEntity.getId());
            if (entity.isEmpty()) {
                throw new NotFoundException("Thương hiệu không tồn tại");
            }
            BrandEntity fake = entity.get();
            brandEntity = brandEntity.toBuilder()
                    .status(fake.getStatus())
                    .created(fake.getCreated())
                    .build();
        }
        brandEntity = _iBrandReponsitory.save(brandEntity);
        if (brandEntity == null) {
            throw new InternalServerException("Lưu thất bại");
        }
        return IntroBrandAdminDto.toDto(brandEntity);
    }

    @Override
    public IntroBrandAdminDto findIntroById(String id) {
        return null;
    }

    @Override
    public FormBrandAdminDto findFormById(String id) {
        Optional<BrandEntity> brandEntity = _iBrandReponsitory.findById(id);
        if (brandEntity.isEmpty()) {
            throw new NotFoundException("Thương hiệu không tồn tại");
        }
        return FormBrandAdminDto.toDto(brandEntity.get());
    }
}
