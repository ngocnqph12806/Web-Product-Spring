package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.ProductDto;
import com.example.webproductspringboot.reponsitory.IProductReponsitory;
import com.example.webproductspringboot.service.intf.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductReponsitory _iProductReponsitory;

    @Override
    public List<ProductDto> findAll() {
        return null;
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
    public ProductDto getMinMaxPrice() {
        return null;
    }

    @Override
    public ProductDto save(ProductDto dto) {
        return null;
    }
//
//    @Override
//    public FilterPriceProductDto getMinMaxPrice() {
//        return FilterPriceProductDto.builder()
//                .min(_iProductReponsitory.findMinPrice())
//                .max(_iProductReponsitory.findMaxPrice())
//                .build();
//    }


//    @Override
//    public boolean changeStatus(String id, Boolean reuslt) {
//        Optional<ProductEntity> optional = _iProductReponsitory.findById(id);
//        if (optional.isEmpty()) {
//            throw new NotFoundException("Sản phẩm không tồn tại");
//        }
//        ProductEntity productEntity = optional.get();
//        productEntity.setStatus(reuslt);
//        if (_iProductReponsitory.save(productEntity) != null) {
//            return true;
//        }
//        return false;
//    }


}
