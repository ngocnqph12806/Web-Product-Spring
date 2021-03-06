package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.BrandDto;
import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.entity.BrandEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IBrandReponsitory;
import com.example.webproductspringboot.service.intf.IBrandService;
import com.example.webproductspringboot.utils.CookieUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BrandService extends AbstractService implements IBrandService {

    private final IBrandReponsitory _iBrandReponsitory;

    protected BrandService(HttpServletRequest request, IBrandReponsitory iBrandReponsitory) {
        super(request);
        _iBrandReponsitory = iBrandReponsitory;
    }

    @Override
    public List<BrandDto> findAll() {
        List<BrandEntity> lst = _iBrandReponsitory.findAll(sortAZ("created"));
        return lst.stream().map(e -> (BrandDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public PageDto<List<BrandDto>> findByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, sortAZ("created"));
        Page<BrandEntity> entities = _iBrandReponsitory.findAll(pageable);
        List<BrandEntity> lst = entities.getContent();
        return new PageDto<>(entities.getTotalPages(), entities.getTotalPages(),
                lst.stream().map(e -> (BrandDto) map(e)).collect(Collectors.toList()));
    }

    @Override
    public BrandDto findById(String id) {
        Optional<BrandEntity> brandEntity = _iBrandReponsitory.findById(id);
        if (brandEntity.isEmpty()) {
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "brand", "brand.not.found"));
        }
        return (BrandDto) map(brandEntity.get());
    }

    @Override
    public BrandDto save(BrandDto dto) {
        BrandEntity entity = (BrandEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(true);
        entity.setCreated(new Date(System.currentTimeMillis()));
        _iBrandReponsitory.save(entity);
        saveHistory(userEntity, "Th??m th????ng hi???u", entity.toString());
        return (BrandDto) map(entity);
    }

    @Override
    public BrandDto update(BrandDto dto) {
        BrandEntity entity = (BrandEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        Optional<BrandEntity> optional = _iBrandReponsitory.findById(entity.getId());
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "brand", "brand.not.found"));
        BrandEntity fake = optional.get();
        if (entity.getStatus() == null) {
            entity.setStatus(fake.getStatus());
        }
        entity.setCreated(fake.getCreated());
        _iBrandReponsitory.save(entity);
        saveHistory(userEntity, "S???a th????ng hi???u", fake + "\n" + entity);
        return (BrandDto) map(entity);
    }

}
