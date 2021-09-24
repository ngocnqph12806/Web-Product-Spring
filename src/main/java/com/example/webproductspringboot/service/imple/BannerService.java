package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.BannerDto;
import com.example.webproductspringboot.entity.BannerEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IBannerReponsitory;
import com.example.webproductspringboot.service.intf.IBannerService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BannerService extends AbstractService implements IBannerService {

    private final IBannerReponsitory _iBannerReponsitory;

    protected BannerService(HttpServletRequest request, IBannerReponsitory iBannerReponsitory) {
        super(request);
        _iBannerReponsitory = iBannerReponsitory;
    }


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
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setCreated(new Date(System.currentTimeMillis()));
        _iBannerReponsitory.save(entity);
        saveHistory(userEntity, "Thêm banner: \n" + entity);
        return (BannerDto) map(entity);
    }

    @Override
    public BannerDto update(BannerDto dto) {
        BannerEntity entity = (BannerEntity) map(dto);
        if (entity == null) throw new BadRequestException("Dữ liệu rỗng");
        UserEntity userEntity = getUserLogin();
        Optional<BannerEntity> optional = _iBannerReponsitory.findById(entity.getId());
        if (optional.isEmpty()) throw new NotFoundException("Banner không tồn tại");
        BannerEntity fake = optional.get();
        entity.setCreated(fake.getCreated());
        _iBannerReponsitory.save(entity);
        saveHistory(userEntity, "Sửa banner: \n" + fake + "\n" + entity);
        return (BannerDto) map(entity);
    }
}
