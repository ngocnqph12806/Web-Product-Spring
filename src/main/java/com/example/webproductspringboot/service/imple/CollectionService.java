package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.CollectionDto;
import com.example.webproductspringboot.entity.CollectionEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.ICollectionReponsitory;
import com.example.webproductspringboot.service.intf.ICollectionService;
import com.example.webproductspringboot.utils.CookieUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollectionService extends AbstractService implements ICollectionService {

    private final ICollectionReponsitory _iCollectionReponsitory;

    protected CollectionService(HttpServletRequest request, ICollectionReponsitory iCollectionReponsitory) {
        super(request);
        _iCollectionReponsitory = iCollectionReponsitory;
    }

    @Override
    public List<CollectionDto> findAll() {
        List<CollectionEntity> lst = _iCollectionReponsitory.findAll(sortAZ("created"));
        return lst.stream().map(e -> (CollectionDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public CollectionDto findById(String idCollection) {
        Optional<CollectionEntity> collectionEntity = _iCollectionReponsitory.findById(idCollection);
        if (collectionEntity.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "collection", "collection.not.found"));
        return (CollectionDto) map(collectionEntity.get());
    }

    @Override
    public CollectionDto save(CollectionDto dto) {
        CollectionEntity entity = (CollectionEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        String idUrl = String.valueOf(new Random().nextInt(20) * 1000000000);
        entity.setIdUrl(Long.parseLong(idUrl.replaceAll("-", "")));
        entity.setPathUrl(idUrl);
        entity.setStatus(true);
        entity.setCreated(new Date(System.currentTimeMillis()));
        _iCollectionReponsitory.save(entity);
        saveHistory(userEntity, "Thêm danh mục", entity.toString());
        return (CollectionDto) map(entity);
    }

    @Override
    public CollectionDto updare(CollectionDto dto) {
        CollectionEntity entity = (CollectionEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        Optional<CollectionEntity> optional = _iCollectionReponsitory.findById(entity.getId());
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "collection", "collection.not.found"));
        CollectionEntity fake = optional.get();
        if (entity.getStatus() == null) {
            entity.setStatus(fake.getStatus());
        }
        entity.setCreated(fake.getCreated());
        _iCollectionReponsitory.save(entity);
        saveHistory(userEntity, "Sửa danh mục", fake + "\n" + entity);
        return (CollectionDto) map(entity);
    }
}
