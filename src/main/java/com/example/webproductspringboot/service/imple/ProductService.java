package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.ProductDto;
import com.example.webproductspringboot.entity.ProductEntity;
import com.example.webproductspringboot.entity.ProductImageEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IProductImageReponsitory;
import com.example.webproductspringboot.reponsitory.IProductReponsitory;
import com.example.webproductspringboot.service.intf.IProductService;
import com.example.webproductspringboot.vo.ProductImageVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService extends AbstractService implements IProductService {

    private final IProductReponsitory _iProductReponsitory;
    private final IProductImageReponsitory _iProductImageReponsitory;

    protected ProductService(HttpServletRequest request, IProductReponsitory iProductReponsitory, IProductImageReponsitory iProductImageReponsitory) {
        super(request);
        _iProductReponsitory = iProductReponsitory;
        _iProductImageReponsitory = iProductImageReponsitory;
    }

    @Override
    public List<ProductDto> findAllProduct() {
        List<ProductEntity> lst = _iProductReponsitory.findAll();
        Collections.reverse(lst);
        return lst.stream().map(e -> (ProductDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public ProductDto findProductById(String id) {
        Optional<ProductEntity> optional = _iProductReponsitory.findById(id);
        if (optional.isEmpty()) throw new NotFoundException("Sản phẩm không tồn tại");
        return (ProductDto) map(optional.get());
    }

    @Override
    public Set<String> getSetCategoryDetailsByIdCategory(String idCategory) {
        if (idCategory == null) {
            return _iProductReponsitory.getAllNameCategory();
        }
        return _iProductReponsitory.getSetCategoryDetailsByIdCategory(idCategory);
    }

    @Override
    public Set<String> getSetColorByIdCategory(String idCategory) {
        if (idCategory == null) {
            return _iProductReponsitory.getAllColor();
        }
        return _iProductReponsitory.getSetColorByIdCategory(idCategory);
    }

    @Override
    public ProductDto saveProduct(ProductDto dto) {
        ProductEntity entity = (ProductEntity) map(dto);
        if (entity == null) throw new BadRequestException("Lỗi dữ liệu");
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(true);
        entity.setIdUrl(new Random().nextLong());
        entity.setCreated(new Date(System.currentTimeMillis()));
        _iProductReponsitory.save(entity);
        saveHistory(userEntity, "Thêm sản phẩm: \n" + entity);
        return (ProductDto) map(entity);
    }

    @Override
    public ProductDto updateProduct(ProductDto dto) {
        ProductEntity entity = (ProductEntity) map(dto);
        if (entity == null) throw new BadRequestException("Lỗi dữ liệu");
        UserEntity userEntity = getUserLogin();
        Optional<ProductEntity> optional = _iProductReponsitory.findById(entity.getId());
        if (optional.isEmpty()) throw new NotFoundException("Sản phẩm không tồn tại");
        ProductEntity fake = optional.get();
        if (entity.getStatus() == null) entity.setStatus(fake.getStatus());
        entity.setIdUrl(fake.getIdUrl());
        entity.setCreated(fake.getCreated());
        _iProductReponsitory.save(entity);
        saveHistory(userEntity, "Sửa sản phẩm: \n" + fake + "\n" + entity);
        return (ProductDto) map(entity);
    }

    @Override
    public ProductImageVo saveImageProduct(ProductImageVo vo) {
        ProductImageEntity entity = (ProductImageEntity) map(vo);
        if (entity == null) throw new BadRequestException("Lỗi dữ liệu");
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        _iProductImageReponsitory.save(entity);
        saveHistory(userEntity, "Thêm sảnh sản phẩm: \n" + entity);
        return (ProductImageVo) map(entity);
    }

    @Override
    public ProductDto getMinMaxPrice() {
        return null;
    }

    @Override
    public void deleteAllImagesByProductId(String id) {
        UserEntity userEntity = getUserLogin();
        _iProductImageReponsitory.deleteAllImagesByProductId(id);
        saveHistory(userEntity, "Xoá toàn bộ ảnh sản phẩm: " + id);
    }


}
