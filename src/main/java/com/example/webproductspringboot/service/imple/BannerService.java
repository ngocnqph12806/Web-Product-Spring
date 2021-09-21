package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.BannerDto;
import com.example.webproductspringboot.entity.BannerEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.InternalServerException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IBannerReponsitory;
import com.example.webproductspringboot.service.intf.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BannerService extends AbstractService implements IBannerService {

    @Autowired
    private IBannerReponsitory _iBannerReponsitory;

    @Override
    public List<BannerDto> findAll() {
        List<BannerEntity> entities = _iBannerReponsitory.findAll();
        Collections.reverse(entities);
        return entities.stream().map(e -> (BannerDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public BannerDto findById(String id) {
        Optional<BannerEntity> optional = _iBannerReponsitory.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Banner không tồn tại");
        }
        return (BannerDto) map(optional.get());
    }

    @Override
    public BannerDto save(BannerDto dto) {
        BannerEntity entity = (BannerEntity) map(dto);
        if (entity == null) throw new BadRequestException("Dữ liệu rỗng");
        entity.setId(UUID.randomUUID().toString());
        entity.setCreated(new Date(System.currentTimeMillis()));
        if (_iBannerReponsitory.save(entity) == null) throw new InternalServerException("Lưu thất bại");
        return (BannerDto) map(entity);
    }

    @Override
    public BannerDto update(BannerDto dto) {
        BannerEntity entity = (BannerEntity) map(dto);
        if (entity == null) throw new BadRequestException("Dữ liệu rỗng");
        dto = findById(entity.getId());
        if (dto == null) throw new NotFoundException("Banner không tồn tại");
        entity.setCreated(dto.getDateCreated());
        if (_iBannerReponsitory.save(entity) == null) throw new InternalServerException("Lưu thất bại");
        return (BannerDto) map(entity);
    }
}
