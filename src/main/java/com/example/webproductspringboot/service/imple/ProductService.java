package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.ProductDto;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.entity.ProductEntity;
import com.example.webproductspringboot.entity.ProductImageEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IProductImageReponsitory;
import com.example.webproductspringboot.reponsitory.IProductReponsitory;
import com.example.webproductspringboot.service.intf.IProductService;
import com.example.webproductspringboot.utils.ContainsUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.ProductImageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService extends AbstractService implements IProductService {

    @Autowired
    private IProductReponsitory _iProductReponsitory;
    @Autowired
    private IProductImageReponsitory _iProductImageReponsitory;

    protected ProductService(HttpServletRequest request) {
        super(request);
    }

    @Override
    public List<ProductDto> findAllProduct() {
        List<ProductEntity> lst = _iProductReponsitory.findAll(sortAZByCreated());
        return lst.stream().map(e -> (ProductDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public PageDto<List<ProductDto>> findByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, sortAZ("created"));
        Page<ProductEntity> entities = _iProductReponsitory.findAll(pageable);
        List<ProductEntity> lst = entities.getContent();
        return new PageDto<>(entities.getTotalPages(), entities.getTotalPages(),
                lst.stream().map(e -> (ProductDto) map(e)).collect(Collectors.toList()));
    }

    @Override
    public ProductDto findProductById(String id) {
        Optional<ProductEntity> optional = _iProductReponsitory.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "product", "product.not.found"));
        return (ProductDto) map(optional.get());
    }

    @Override
    public Set<String> getSetCategoryDetailsByIdCategory(String idCategory) {
        if (idCategory == null) return _iProductReponsitory.getAllNameCategory();
        return _iProductReponsitory.getSetCategoryDetailsByIdCategory(idCategory);
    }

    @Override
    public Set<String> getSetColorByIdCategory(String idCategory) {
        if (idCategory == null) return _iProductReponsitory.getAllColor();
        return _iProductReponsitory.getSetColorByIdCategory(idCategory);
    }

    @Override
    public ProductDto saveProduct(ProductDto dto) {
        ProductEntity entity = (ProductEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(true);
        String idUrl = String.valueOf(new Random().nextInt(20) * 1000000000);
        entity.setIdUrl(Long.parseLong(idUrl.replaceAll("-", "")));
        entity.setCreated(new Date(System.currentTimeMillis()));
        _iProductReponsitory.save(entity);
        saveHistory(userEntity, "Thêm sản phẩm", entity.toString());
        return (ProductDto) map(entity);
    }

    @Override
    public ProductDto updateProduct(ProductDto dto) {
        ProductEntity entity = (ProductEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        Optional<ProductEntity> optional = _iProductReponsitory.findById(entity.getId());
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "product", "product.not.found"));
        ProductEntity fake = optional.get();
        if (entity.getStatus() == null) entity.setStatus(fake.getStatus());
        entity.setIdUrl(fake.getIdUrl());
        entity.setCreated(fake.getCreated());
        _iProductReponsitory.save(entity);
        saveHistory(userEntity, "Sửa sản phẩm", fake + "\n" + entity);
        return (ProductDto) map(entity);
    }

    @Override
    public void saveImageProduct(ProductImageVo vo) {
        ProductImageEntity entity = (ProductImageEntity) map(vo);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        _iProductImageReponsitory.save(entity);
        saveHistory(userEntity, "Thêm sảnh sản phẩm", entity.toString());
    }

    @Override
    public ProductDto getMinMaxPrice() {
        return null;
    }

    @Override
    public void deleteAllImagesByProductId(String id) {
        UserEntity userEntity = getUserLogin();
        _iProductImageReponsitory.deleteAllImagesByProductId(id);
        saveHistory(userEntity, "Xoá toàn bộ ảnh sản phẩm", "Xoá toàn bộ ảnh sản phẩm: " + id);
    }

}
