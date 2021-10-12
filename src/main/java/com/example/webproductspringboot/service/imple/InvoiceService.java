package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.InvoiceDetailDto;
import com.example.webproductspringboot.dto.InvoiceDto;
import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.entity.InvoiceDetailsEntity;
import com.example.webproductspringboot.entity.InvoiceEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.InvoiceDetailsReponsitory;
import com.example.webproductspringboot.reponsitory.InvoiceReponsitory;
import com.example.webproductspringboot.service.intf.IInvoiceService;
import com.example.webproductspringboot.utils.CookieUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InvoiceService extends AbstractService implements IInvoiceService {

    private final InvoiceReponsitory _invoiceReponsitory;
    private final InvoiceDetailsReponsitory _invoiceDetailsReponsitory;

    protected InvoiceService(HttpServletRequest request, InvoiceReponsitory invoiceReponsitory, InvoiceDetailsReponsitory invoiceDetailsReponsitory) {
        super(request);
        _invoiceReponsitory = invoiceReponsitory;
        _invoiceDetailsReponsitory = invoiceDetailsReponsitory;
    }

    @Override
    public List<InvoiceDto> findAll() {
        List<InvoiceEntity> lst = _invoiceReponsitory.findAll(sortAZ("created"));
        return lst.stream().map(e -> (InvoiceDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public PageDto<List<InvoiceDto>> findByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, sortAZ("created"));
        Page<InvoiceEntity> entities = _invoiceReponsitory.findAll(pageable);
        List<InvoiceDto> lst = entities.getContent().stream().map(e -> (InvoiceDto) map(e)).collect(Collectors.toList());
        return new PageDto<>(entities.getTotalPages(), entities.getTotalPages(), lst);
    }

    @Override
    public InvoiceDto findById(String id) {
        Optional<InvoiceEntity> optional = _invoiceReponsitory.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "invoice", "invoice.not.found"));
        return (InvoiceDto) map(optional.get());
    }

    @Override
    public InvoiceDto save(InvoiceDto dto) {
        InvoiceEntity entity = (InvoiceEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setCreated(new Date(System.currentTimeMillis()));
        entity.setStatus(false);
        entity.setIdStaffCreate(userEntity);
        _invoiceReponsitory.save(entity);
        saveHistory(userEntity, "Thêm hoá đơn nhập hàng", entity.toString());
        return (InvoiceDto) map(entity);
    }

    @Override
    public InvoiceDto update(InvoiceDto dto) {
        InvoiceEntity entity = (InvoiceEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        Optional<InvoiceEntity> optional = _invoiceReponsitory.findById(entity.getId());
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "invoice", "invoice.not.found"));
        InvoiceEntity fake = optional.get();
        if (entity.getStatus() == null) entity.setStatus(fake.getStatus());
        entity.setCreated(fake.getCreated());
        entity.setIdStaffCreate(fake.getIdStaffCreate());
        _invoiceReponsitory.save(entity);
        saveHistory(userEntity, "Sửa hoá đơn nhập hàng", fake + "\n" + entity);
        return (InvoiceDto) map(entity);
    }

    @Override
    public void saveDetailInvoice(InvoiceDetailDto detail) {
        InvoiceDetailsEntity entity = (InvoiceDetailsEntity) map(detail);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        _invoiceDetailsReponsitory.save(entity);
        saveHistory(userEntity, "Thêm chi tiết hoá đơn nhập hàng", entity.toString());
    }

    @Override
    public void removeInvoice(InvoiceDto dtoFake) {
        _invoiceDetailsReponsitory.deleteAllByIdInvoice(dtoFake.getId());
        _invoiceReponsitory.deleteById(dtoFake.getId());
    }

    @Override
    public void removeDetailInvoiceById(String id) {
        _invoiceDetailsReponsitory.deleteById(id);
    }
}
