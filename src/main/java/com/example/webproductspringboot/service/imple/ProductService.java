package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.product.DetailsProductDto;
import com.example.webproductspringboot.dto.product.FilterPriceProductDto;
import com.example.webproductspringboot.dto.product.FormProductDto;
import com.example.webproductspringboot.dto.product.IntroProductDto;
import com.example.webproductspringboot.reponsitory.IProductReponsitory;
import com.example.webproductspringboot.service.intf.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductReponsitory _iProductReponsitory;

    @Override
    public List<IntroProductDto> findBestSallers() {
        return _iProductReponsitory.findBestSallers().stream().map(IntroProductDto::toDto).collect(Collectors.toList());
    }

    @Override
    public List<IntroProductDto> findAll() {
        return _iProductReponsitory.findAll().stream().map(IntroProductDto::toDto).collect(Collectors.toList());
    }

    @Override
    public List<IntroProductDto> findByIdCategory(String idCategory) {
        return _iProductReponsitory.findByIdCategory(idCategory).stream().map(IntroProductDto::toDto).collect(Collectors.toList());
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
    public IntroProductDto findIntroById(String id) {
        return null;
    }

    @Override
    public IntroProductDto save(FormProductDto dto) {
        return null;
    }


}
