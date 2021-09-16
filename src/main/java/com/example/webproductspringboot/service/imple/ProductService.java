package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.product.*;
import com.example.webproductspringboot.entity.ProductEntity;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IProductReponsitory;
import com.example.webproductspringboot.service.intf.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductReponsitory _iProductReponsitory;

    @Override
    public List<IntroProductWebDto> findBestSallers() {
        return _iProductReponsitory.findBestSallers().stream().map(IntroProductWebDto::toDto).collect(Collectors.toList());
    }

    @Override
    public List<IntroProductWebDto> findAllIntroWeb() {
        return _iProductReponsitory.findAll().stream().map(IntroProductWebDto::toDto).collect(Collectors.toList());
    }

    @Override
    public List<IntroProductAdminDto> findAllIntroAdmin() {
        return _iProductReponsitory.findAll().stream().map(IntroProductAdminDto::toDto).collect(Collectors.toList());
    }

    @Override
    public List<IntroProductWebDto> findByIdCategory(String idCategory) {
        return _iProductReponsitory.findByIdCategory(idCategory).stream().map(IntroProductWebDto::toDto).collect(Collectors.toList());
    }

    @Override
    public DetailsProductDto findByPath(Long idUrl, String pathUrl) {
        return DetailsProductDto.toDto(_iProductReponsitory.findByPath(idUrl, pathUrl).orElse(null));
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
    public FilterPriceProductDto getMinMaxPrice() {
        return FilterPriceProductDto.builder()
                .min(_iProductReponsitory.findMinPrice())
                .max(_iProductReponsitory.findMaxPrice())
                .build();
    }

    @Override
    public IntroProductWebDto findIntroById(String id) {
        return null;
    }

    @Override
    public FormProductDto findFormById(String id) {
        return null;
    }

    @Override
    public IntroProductWebDto save(FormProductDto dto) {
        return null;
    }

    @Override
    public boolean changeStatus(String id, Boolean reuslt) {
        Optional<ProductEntity> optional = _iProductReponsitory.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Sản phẩm không tồn tại");
        }
        ProductEntity productEntity = optional.get();
        productEntity.setStatus(reuslt);
        if (_iProductReponsitory.save(productEntity) != null) {
            return true;
        }
        return false;
    }


}
