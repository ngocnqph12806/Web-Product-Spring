package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IProductService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.utils.MapperModelUtils;
import com.example.webproductspringboot.vo.ProductImageVo;
import com.example.webproductspringboot.vo.SearchProductVo;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:3000/")
public class ProductApi extends AbstractApi {

    private final IProductService _iProductService;

    protected ProductApi(HttpServletRequest request, IProductService iProductService) {
        super(request);
        _iProductService = iProductService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(SearchProductVo searchProductVo) {
        List<ProductDto> lst = _iProductService.findAllProduct();
        lst = search(lst, searchProductVo, searchProductVo.getName(), 0);
//        ResultDto<List<ProductDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(lst);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iProductService.findProductById(id)));
    }

    @GetMapping(value = "/{id}", params = "modal")
    public ResponseEntity<?> getByIdWithModal(@PathVariable("id") String id) {
        ResultDto<ProductDto> result = new ResultDto<>(OK, null);
        try {
            result.setData(_iProductService.findProductById(id));
        } catch (Exception e) {
            result.setData(new ProductDto());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Validated @RequestBody ProductFormDto form, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "product", errors.getFieldErrors().get(0).getDefaultMessage()));
        List<String> lstImages = new ArrayList<>();
        for (String x : form.getImages()) if (x != null && !x.isEmpty()) lstImages.add(x);
        if (lstImages.isEmpty())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "product", "image.please.choose.product.image"));
        ProductDto dtoSave = _iProductService.saveProduct(form.toDto());
        if (dtoSave != null) System.out.println("Lưu ảnh"); for (String x : lstImages)
            _iProductService.saveImageProduct(ProductImageVo.builder().path(x).idProduct(dtoSave.getId()).build());
        ResultDto<ProductDto> result = new ResultDto<>(CREATED, _iProductService.findProductById(dtoSave.getId()));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid ProductFormDto form, Errors errors) {
        System.out.println(form);
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "product", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (!form.getId().equals(id))
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ProductDto dtoSave = _iProductService.updateProduct(form.toDto());
        if (dtoSave != null) {
            if (form.getImages() != null && form.getImages().length > 0) {
                _iProductService.deleteAllImagesByProductId(dtoSave.getId());
                for (String x : form.getImages())
                    if (x != null && !x.isEmpty())
                        _iProductService.saveImageProduct(ProductImageVo.builder().path(x).idProduct(dtoSave.getId()).build());
            }
        }
        ResultDto<ProductDto> result = new ResultDto<>(UPDATED, _iProductService.findProductById(dtoSave.getId()));
        return ResponseEntity.ok(result);
    }

    private List<ProductDto> search(List<ProductDto> lst, SearchProductVo obj, String[] type, Integer index) {
        String[] arrFake = new String[0];
        if (type != null) arrFake = type;
        for (String x : arrFake) {
            switch (index) {
                case 0:
                    lst = lst.stream().filter(e -> e.getName().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 1:
                    lst = lst.stream().filter(e -> e.getIdBrand().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 2:
                    lst = lst.stream().filter(e -> e.getNameBrand().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 3:
                    lst = lst.stream().filter(e -> e.getIdCategory().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 4:
                    lst = lst.stream().filter(e -> e.getNameCategory().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 5:
                    lst = lst.stream().filter(e -> e.getPrice().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 6:
                    lst = lst.stream().filter(e -> e.getPriceSale().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 7:
                    lst = lst.stream().filter(e -> e.getQuantity().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 8:
                    lst = lst.stream().filter(e -> e.getColor().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 9:
                    lst = lst.stream().filter(e -> e.getCategoryDetails().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 10:
                    lst = lst.stream().filter(e -> e.getLocation().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 11:
                    lst = lst.stream().filter(e -> e.getPath().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 12:
                    lst = lst.stream().filter(e -> e.getIdPath().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 13:
                    lst = lst.stream().filter(e -> e.getPathUserManual().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 14:
                    lst = lst.stream().filter(e -> e.getDescription().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 15:
                    lst = lst.stream().filter(e -> e.getStatus().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 16:
                    lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateCreated()).contains(x.toLowerCase())).collect(Collectors.toList());
            }
        }
        switch (index) {
            case 0:
                return search(lst, obj, obj.getIdBrand(), 1);
            case 1:
                return search(lst, obj, obj.getNameBrand(), 2);
            case 2:
                return search(lst, obj, obj.getIdCategory(), 3);
            case 3:
                return search(lst, obj, obj.getNameCategory(), 4);
            case 4:
                return search(lst, obj, obj.getPrice(), 5);
            case 5:
                return search(lst, obj, obj.getPriceSale(), 6);
            case 6:
                return search(lst, obj, obj.getQuantity(), 7);
            case 7:
                return search(lst, obj, obj.getColor(), 8);
            case 8:
                return search(lst, obj, obj.getCategoryDetails(), 9);
            case 9:
                return search(lst, obj, obj.getLocation(), 10);
            case 10:
                return search(lst, obj, obj.getPath(), 11);
            case 11:
                return search(lst, obj, obj.getIdPath(), 12);
            case 12:
                return search(lst, obj, obj.getPathUserManual(), 13);
            case 13:
                return search(lst, obj, obj.getDescription(), 14);
            case 14:
                return search(lst, obj, obj.getStatus(), 15);
            case 15:
                return search(lst, obj, obj.getDateCreated(), 16);
            default:
                return lst;
        }
    }
}


