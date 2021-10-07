package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.OrderDto;
import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.TransportDto;
import com.example.webproductspringboot.entity.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.ITransportReponsitory;
import com.example.webproductspringboot.service.intf.ITransportService;
import com.example.webproductspringboot.utils.CookieUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransportService extends AbstractService implements ITransportService {

    private final ITransportReponsitory _iTransportReponsitory;

    protected TransportService(HttpServletRequest request, ITransportReponsitory iTransportReponsitory) {
        super(request);
        _iTransportReponsitory = iTransportReponsitory;
    }

    @Override
    public List<TransportDto> findAll() {
        List<TransportEntity> lst = _iTransportReponsitory.findAll();
        Collections.reverse(lst);
        return lst.stream().map(e -> (TransportDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public PageDto<List<TransportDto>> findByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, sortAZ("created"));
        Page<TransportEntity> entities = _iTransportReponsitory.findAll(pageable);
        List<TransportDto> lst = entities.getContent().stream().map(e -> (TransportDto) map(e)).collect(Collectors.toList());
        return new PageDto<>(entities.getTotalPages(), entities.getTotalPages(), lst);
    }

    @Override
    public TransportDto findById(String id) {
        Optional<TransportEntity> optional = _iTransportReponsitory.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "transport", "transport.not.found"));
        return (TransportDto) map(optional.get());
    }

    @Override
    public TransportDto save(TransportDto dto) {
        TransportEntity entity = (TransportEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(true);
        entity.setCreated(new Date(System.currentTimeMillis()));
        _iTransportReponsitory.save(entity);
        saveHistory(userEntity, "Thêm hoá đơn vận chuyển", entity.toString());
        return (TransportDto) map(entity);
    }

    @Override
    public TransportDto update(TransportDto dto) {
        TransportEntity entity = (TransportEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        Optional<TransportEntity> optional = _iTransportReponsitory.findById(entity.getId());
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "transport", "transport.not.found"));
        TransportEntity fake = optional.get();
        if (entity.getStatus() == null) entity.setStatus(fake.getStatus());
        entity.setCreated(fake.getCreated());
        _iTransportReponsitory.save(entity);
        saveHistory(userEntity, "Sửa hoá đơn vận chuyển", fake + "\n" + entity);
        return (TransportDto) map(entity);
    }
}
