package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.CollectionDto;
import com.example.webproductspringboot.entity.CollectionEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.InternalServerException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.ICollectionReponsitory;
import com.example.webproductspringboot.service.intf.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollectionService extends AbstractService implements ICollectionService {

    @Autowired
    private ICollectionReponsitory _iCollectionReponsitory;

    @Override
    public List<CollectionDto> findAll() {
        List<CollectionEntity> lst = _iCollectionReponsitory.findAll();
        Collections.reverse(lst);
        return lst.stream().map(e -> (CollectionDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public CollectionDto findById(String idCollection) {
        Optional<CollectionEntity> collectionEntity = _iCollectionReponsitory.findById(idCollection);
        if (collectionEntity.isEmpty()) throw new NotFoundException("Danh mục không tồnt ại");
        return (CollectionDto) map(collectionEntity.get());
    }

    @Override
    public CollectionDto save(CollectionDto dto) {
        CollectionEntity entity = (CollectionEntity) map(dto);
        if (entity == null) throw new BadRequestException("Dữ liệu lỗi");
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(true);
        entity.setCreated(new Date(System.currentTimeMillis()));
        if (_iCollectionReponsitory.save(entity) == null) throw new InternalServerException("Lưu thất bại");
        return (CollectionDto) map(entity);
    }

    @Override
    public CollectionDto updare(CollectionDto dto) {
        CollectionEntity entity = (CollectionEntity) map(dto);
        if (entity == null) throw new BadRequestException("Dữ liệu lỗi");
        Optional<CollectionEntity> optional = _iCollectionReponsitory.findById(entity.getId());
        if (optional.isEmpty()) throw new NotFoundException("Danh mục không tồn tại");
        CollectionEntity fake = optional.get();
        if (entity.getStatus() == null) {
            entity.setStatus(fake.getStatus());
        }
        entity.setCreated(fake.getCreated());
        if (_iCollectionReponsitory.save(entity) == null) throw new InternalServerException("Lưu thất bại");
        return (CollectionDto) map(entity);
    }
}
