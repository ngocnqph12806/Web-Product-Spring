package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IProductService;
import com.example.webproductspringboot.utils.MapperModelUtils;
import com.example.webproductspringboot.vo.ProductImageVo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductApi {

    @Autowired
    private IProductService _iProductService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(true, "", _iProductService.findProductById(id)));
    }

    @GetMapping(value = "/{id}", params = "modal")
    public ResponseEntity<?> getByIdWithModal(@PathVariable("id") String id) {
        ResultDto<ProductDto> result = new ResultDto<>(true, "", null);
        try {
            result.setData(_iProductService.findProductById(id));
        } catch (Exception e) {
            result.setData(new ProductDto());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Validated @RequestBody ProductForm form, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        List<String> lstImages = new ArrayList<>();
        for (String x : form.getImages()) if (x != null && !x.isEmpty()) lstImages.add(x);
        if (lstImages.isEmpty()) throw new BadRequestException("Vui lòng chọn hình ản sản phẩm");
        ProductDto dtoSave = _iProductService.saveProduct(form.toDto());
        if (dtoSave != null) for (String x : lstImages)
            _iProductService.saveImageProduct(ProductImageVo.builder().path(x).idProduct(dtoSave.getId()).build());
        ResultDto<ProductDto> result = new ResultDto<>(true, "Lưu thành công", _iProductService.findProductById(dtoSave.getId()));
        return ResponseEntity.ok(result);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody @Valid ProductForm form, Errors errors) {
        System.out.println(form);
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ProductDto dtoSave = _iProductService.updateProduct(form.toDto());
        if (dtoSave != null) {
            if (form.getImages() != null && form.getImages().length > 0) {
                _iProductService.deleteAllImagesByProductId(dtoSave.getId());
                for (String x : form.getImages())
                    if (x != null && !x.isEmpty())
                        _iProductService.saveImageProduct(ProductImageVo.builder().path(x).idProduct(dtoSave.getId()).build());
            }
        }
        ResultDto<ProductDto> result = new ResultDto<>(true, "Lưu thành công", _iProductService.findProductById(dtoSave.getId()));
        return ResponseEntity.ok(result);
    }

    @Data
    class ProductForm {

        private String id;
        private String name;
        private String idBrand;
        private String idCategory;
        private Long price;
        private Long priceSale;
        private Integer quantity;
        private String color;
        private String categoryDetails;
        private String location;
        private String path;
        private String pathUserManual;
        private String description;
        private Boolean status;
        private String[] images;

        public ProductDto toDto() {
            return (ProductDto) MapperModelUtils.get().toDto(this, ProductDto.class);
        }
    }
}


