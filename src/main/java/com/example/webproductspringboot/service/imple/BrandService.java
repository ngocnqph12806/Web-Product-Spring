package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.BrandDto;
import com.example.webproductspringboot.entity.BrandEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.InternalServerException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IBrandReponsitory;
import com.example.webproductspringboot.service.intf.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BrandService extends AbstractService implements IBrandService {

    @Autowired
    private IBrandReponsitory _iBrandReponsitory;

    @Override
    public List<BrandDto> findAll() {
        List<BrandEntity> lst = _iBrandReponsitory.findAll();
        Collections.reverse(lst);
        return lst.stream().map(e -> (BrandDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public BrandDto findById(String id) {
        Optional<BrandEntity> brandEntity = _iBrandReponsitory.findById(id);
        if (brandEntity.isEmpty()) {
            throw new NotFoundException("Thương hiệu không tồn tại");
        }
        return (BrandDto) map(brandEntity.get());
    }

    @Override
    public BrandDto save(BrandDto dto) {
        BrandEntity entity = (BrandEntity) map(dto);
        if (entity == null) throw new BadRequestException("Lỗi dữ liệu");
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(true);
        entity.setCreated(new Date(System.currentTimeMillis()));
        if (_iBrandReponsitory.save(entity) == null) throw new InternalServerException("Lưu thất bại");
        return (BrandDto) map(entity);
    }

    @Override
    public BrandDto update(BrandDto dto) {
        BrandEntity entity = (BrandEntity) map(dto);
        if (entity == null) throw new BadRequestException("Lỗi dữ liệu");
        Optional<BrandEntity> optional = _iBrandReponsitory.findById(entity.getId());
        if (optional.isEmpty()) throw new NotFoundException("Thương hiệu không tồn tại");
        BrandEntity fake = optional.get();
        if (entity.getStatus() == null) {
            entity.setStatus(fake.getStatus());
        }
        entity.setCreated(fake.getCreated());
        if (_iBrandReponsitory.save(entity) == null) throw new InternalServerException("Lưu thất bại");
        return (BrandDto) map(entity);
    }

}
