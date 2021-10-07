package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.ReturnDto;
import com.example.webproductspringboot.dto.VoucherDto;
import com.example.webproductspringboot.entity.CustomersReturnEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.entity.VoucherEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IVoucherReponsitory;
import com.example.webproductspringboot.service.intf.IVoucherService;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.utils.MapperModelUtils;
import com.example.webproductspringboot.vo.VoucherApplyVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VoucherService extends AbstractService implements IVoucherService {

    private final IVoucherReponsitory _iVoucherReponsitory;

    protected VoucherService(HttpServletRequest request, IVoucherReponsitory iVoucherReponsitory) {
        super(request);
        _iVoucherReponsitory = iVoucherReponsitory;
    }

    @Override
    public List<VoucherDto> findAll() {
        List<VoucherEntity> lst = _iVoucherReponsitory.findAll();
        Collections.reverse(lst);
        return lst.stream().map(e -> (VoucherDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public PageDto<List<VoucherDto>> findByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, sortAZ("created"));
        Page<VoucherEntity> entities = _iVoucherReponsitory.findAll(pageable);
        List<VoucherDto> lst = entities.getContent().stream().map(e -> (VoucherDto) map(e)).collect(Collectors.toList());
        return new PageDto<>(entities.getTotalPages(), entities.getTotalPages(), lst);
    }

    @Override
    public VoucherDto findById(String id) {
        Optional<VoucherEntity> optional = _iVoucherReponsitory.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "voucher", "voucher.not.found"));
        return (VoucherDto) map(optional.get());
    }

    @Override
    public VoucherDto save(VoucherDto dto) {
        VoucherEntity entity = (VoucherEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setIdStaff(userEntity);
        entity.setStatus(true);
        entity.setCreated(new Date(System.currentTimeMillis()));
        _iVoucherReponsitory.save(entity);
        saveHistory(userEntity, "Thêm mã giảm giá", entity.toString());
        return (VoucherDto) map(entity);
    }

    @Override
    public VoucherDto update(VoucherDto dto) {
        VoucherEntity entity = (VoucherEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        Optional<VoucherEntity> optional = _iVoucherReponsitory.findById(entity.getId());
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "voucher", "voucher.not.found"));
        VoucherEntity fake = optional.get();
        if (entity.getStatus() == null) {
            entity.setStatus(fake.getStatus());
        }
        entity.setIdStaff(fake.getIdStaff());
        entity.setCreated(fake.getCreated());
        _iVoucherReponsitory.save(entity);
        saveHistory(userEntity, "Sửa mã giảm giá", fake + "\n" + entity);
        return (VoucherDto) map(entity);
    }

    @Override
    public VoucherApplyVo findByCode(String code) {
        Optional<VoucherEntity> optional = _iVoucherReponsitory.findByCode(code);
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "voucher", "voucher.not.found"));
        return entityToVoucherApplyVo(optional.get());
    }

    private VoucherApplyVo entityToVoucherApplyVo(VoucherEntity entity) {
        return VoucherApplyVo.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .priceSale(entity.getPriceSale())
                .build();
    }

}
