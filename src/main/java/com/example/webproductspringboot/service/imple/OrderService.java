package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.ChechoutDto;
import com.example.webproductspringboot.dto.OrderDetailDto;
import com.example.webproductspringboot.dto.OrderDto;
import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.entity.OrderDetailsEntity;
import com.example.webproductspringboot.entity.OrderEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IOrderDtailsReponsitory;
import com.example.webproductspringboot.reponsitory.IOrderReponsitory;
import com.example.webproductspringboot.service.intf.IOrderService;
import com.example.webproductspringboot.utils.CookieUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService extends AbstractService implements IOrderService {

    private final IOrderReponsitory _iOrderReponsitory;
    private final IOrderDtailsReponsitory _iOrderDtailsReponsitory;

    protected OrderService(HttpServletRequest request, IOrderReponsitory iOrderReponsitory, IOrderDtailsReponsitory iOrderDtailsReponsitory) {
        super(request);
        _iOrderReponsitory = iOrderReponsitory;
        _iOrderDtailsReponsitory = iOrderDtailsReponsitory;
    }

    @Override
    public List<OrderDto> findAll() {
        List<OrderEntity> lst = _iOrderReponsitory.findAll(sortAZ("created"));
        return lst.stream().map(e -> (OrderDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public PageDto<List<OrderDto>> findByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, sortAZ("created"));
        Page<OrderEntity> entities = _iOrderReponsitory.findAll(pageable);
        List<OrderDto> lst = entities.getContent().stream().map(e -> (OrderDto) map(e)).collect(Collectors.toList());
        return new PageDto<>(entities.getTotalPages(), entities.getTotalPages(), lst);
    }

    @Override
    public OrderDto findById(String id) {
        Optional<OrderEntity> optional = _iOrderReponsitory.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "order", "order.not.found"));
        return (OrderDto) map(optional.get());
    }

    @Override
    public OrderDetailDto findOrderDetailById(String idOrderDetail) {
        Optional<OrderDetailsEntity> optional = _iOrderDtailsReponsitory.findById(idOrderDetail);
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "order", "order.detail.not.found"));
        return (OrderDetailDto) map(optional.get());
    }

    @Override
    public OrderDto saveCheckout(ChechoutDto dto) {
        OrderEntity entity = (OrderEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(false);
        entity.setCreated(new Date(System.currentTimeMillis()));
        entity.setIdVisit(UserEntity.builder().id(dto.getIdUser()).build());
        entity.setStaffCreate(UserEntity.builder().id(dto.getIdUser()).build());
        entity.setStaffSales(UserEntity.builder().id(dto.getIdUser()).build());
        _iOrderReponsitory.save(entity);
        saveHistory(userEntity, "Thêm hoá đơn khách đặt hàng", entity.toString());
        return (OrderDto) map(entity);
    }

    @Override
    public List<OrderDto> getAllOrderByUserLogin(String id) {
        return _iOrderReponsitory.getAllOrderByUserLogin(id).stream().map(e -> (OrderDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public OrderDto save(OrderDto dto) {
        OrderEntity entity = (OrderEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(false);
        entity.setCreated(new Date(System.currentTimeMillis()));
        entity.setStaffCreate(userEntity);
        _iOrderReponsitory.save(entity);
        saveHistory(userEntity, "Thêm hoá đơn đặt hàng", entity.toString());
        return (OrderDto) map(entity);
    }

    @Override
    public OrderDto update(OrderDto dto) {
        OrderEntity entity = (OrderEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        Optional<OrderEntity> optional = _iOrderReponsitory.findById(entity.getId());
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "order", "order.not.found"));
        OrderEntity fake = optional.get();
        if (entity.getStatus() == null) entity.setStatus(fake.getStatus());
        if (entity.getPaymentStatus() == null) entity.setPaymentStatus(fake.getPaymentStatus());
        entity.setCreated(fake.getCreated());
        entity.setStaffCreate(fake.getStaffCreate());
        _iOrderReponsitory.save(entity);
        saveHistory(userEntity, "Sửa hoá đơn đặt hàng", fake + "\n" + entity);
        return (OrderDto) map(entity);
    }

    @Override
    public void updatePayment(String id) {
        Optional<OrderEntity> optional = _iOrderReponsitory.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "order", "order.not.found"));
        OrderEntity fake = optional.get();
        fake.setPaymentStatus(true);
        _iOrderReponsitory.save(fake);
        saveHistory(null, "Thanh toán hoá đơn mua hàng", fake + "\n" + fake);
    }

    @Override
    public List<Long> findAllDoanhSoTrongNam() {
        List<Long> lst = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            lst.add(_iOrderReponsitory.finDoanhSoTrongThang(i + 1));
        }
        return lst;
    }

    @Override
    public List<Long> findAllDoanhThuTrongNam() {
        List<Long> lst = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Long thang = _iOrderReponsitory.finDoanhThuTrongThang(i + 1);
            if(thang == null){
                thang = Long.valueOf("0");
            }
            lst.add(thang);
        }
        return lst;
    }

    @Override
    public void saveDetailOrder(OrderDetailDto x) {
        OrderDetailsEntity entity = (OrderDetailsEntity) map(x);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        _iOrderDtailsReponsitory.save(entity);
        saveHistory(userEntity, "Thêm chi tiết hoá đơn đặt hàng", entity.toString());
    }

    @Override
    public void removeOrder(OrderDto dtoSave) {
        _iOrderDtailsReponsitory.deleteAllByIdOrder(dtoSave.getId());
        _iOrderReponsitory.deleteById(dtoSave.getId());
    }

    @Override
    public void removeDetailOrderById(String id) {
        _iOrderDtailsReponsitory.deleteById(id);
    }

}
