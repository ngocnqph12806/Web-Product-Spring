package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.InvoiceDto;
import com.example.webproductspringboot.entity.InvoiceEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.InvoiceReponsitory;
import com.example.webproductspringboot.service.intf.IInvoiceService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InvoiceService extends AbstractService implements IInvoiceService {

    private final InvoiceReponsitory _invoiceReponsitory;

    protected InvoiceService(HttpServletRequest request, InvoiceReponsitory invoiceReponsitory) {
        super(request);
        _invoiceReponsitory = invoiceReponsitory;
    }

    @Override
    public List<InvoiceDto> findAll() {
        List<InvoiceEntity> lst = _invoiceReponsitory.findAll();
        Collections.reverse(lst);
        return lst.stream().map(e -> (InvoiceDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public InvoiceDto findById(String id) {
        Optional<InvoiceEntity> optional = _invoiceReponsitory.findById(id);
        if (optional.isEmpty()) throw new NotFoundException("Hoá đơn nhập hàng không tồn tại");
        return (InvoiceDto) map(optional.get());
    }

    @Override
    public InvoiceDto save(InvoiceDto dto) {
        InvoiceEntity entity = (InvoiceEntity) map(dto);
        if (entity == null) throw new BadRequestException("Lỗi dữ liệu");
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setCreated(new Date(System.currentTimeMillis()));
        entity.setStatus(true);
        entity.setIdStaffCreate(userEntity);
        _invoiceReponsitory.save(entity);
        saveHistory(userEntity, "Thêm hoá đơn nhập hàng: \n" + entity);
        return (InvoiceDto) map(entity);
    }

    @Override
    public InvoiceDto update(InvoiceDto dto) {
        InvoiceEntity entity = (InvoiceEntity) map(dto);
        if (entity == null) throw new BadRequestException("Lỗi dữ liệu");
        UserEntity userEntity = getUserLogin();
        Optional<InvoiceEntity> optional = _invoiceReponsitory.findById(entity.getId());
        if (optional.isEmpty()) throw new NotFoundException("Hoá đơn nhập hàng không tồn tại");
        InvoiceEntity fake = optional.get();
        if (entity.getStatus() == null) entity.setStatus(fake.getStatus());
        entity.setCreated(fake.getCreated());
        entity.setIdStaffCreate(fake.getIdStaffCreate());
        _invoiceReponsitory.save(entity);
        saveHistory(userEntity, "Sửa hoá đơn nhập hàng: \n" + fake + "\n" + entity);
        return (InvoiceDto) map(entity);
    }
}
