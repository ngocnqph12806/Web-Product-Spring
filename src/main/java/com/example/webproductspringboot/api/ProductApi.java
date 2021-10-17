package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.service.intf.IProductService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.ProductImageVo;
import com.example.webproductspringboot.vo.SearchProductVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/product")
//@CrossOrigin(origins = "http://localhost:3000/")
public class ProductApi extends AbstractApi {

    private final IProductService _iProductService;

    protected ProductApi(HttpServletRequest request, HttpServletResponse response, IProductService iProductService) {
        super(request, response);
        _iProductService = iProductService;
    }

    @GetMapping
    public ResponseEntity<?> getAllByPageAndFilter(SearchProductVo searchProductVo) {
        if (searchProductVo.getP() == null || searchProductVo.getS() == null
                || searchProductVo.getField() == null || searchProductVo.getField().isEmpty()
                || searchProductVo.getSort() == null || searchProductVo.getSort().isEmpty()) {
            return ResponseEntity.ok(_iProductService.findAllProduct());
        }
        List<ProductDto> lst = _iProductService
                .findByPageAndSort(searchProductVo.getP(), searchProductVo.getS(), searchProductVo.getField(), searchProductVo.getSort())
                .getContent();
        Set<ProductDto> lstFake = new HashSet<>();
        ResultSearchDto<Set<ProductDto>> result = search(lstFake, lst, searchProductVo, searchProductVo.getName(), 0, true);
        return ResponseEntity.ok(result.getResult().isEmpty() && result.getIsNull() ? lst : result.getResult());
    }

//    @GetMapping(params = "_p")
//    public ResponseEntity<?> getAllByPageAndSort(@RequestParam("_p") Integer page, @RequestParam("_s") Integer size,
//                                                 @RequestParam(value = "_sort", defaultValue = "desc") String sort,
//                                                 @RequestParam(value = "_field", defaultValue = "created") String field) {
//        return ResponseEntity.ok(_iProductService.findByPageAndSort(page, size, field, sort));
//    }

    @GetMapping(path = "/by-most-order")
    public ResponseEntity<?> getByMostOrder() {
        return ResponseEntity.ok(_iProductService.getByMostOrder());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(_iProductService.findProductById(id));
    }

    @GetMapping(value = "/{id}", params = "modal")
    public ResponseEntity<?> getByIdWithModal(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(_iProductService.findProductById(id));
        } catch (Exception e) {
        }
        return ResponseEntity.ok(new ProductDto());
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
        if (dtoSave != null) System.out.println("Lưu ảnh");
        for (String x : lstImages)
            _iProductService.saveImageProduct(ProductImageVo.builder().path(x).idProduct(dtoSave.getId()).build());
//        ResultDto<ProductDto> result = new ResultDto<>(CREATED, _iProductService.findProductById(dtoSave.getId()));
        return ResponseEntity.ok(_iProductService.findProductById(dtoSave.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid ProductFormDto form, Errors errors) {
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
//        ResultDto<ProductDto> result = new ResultDto<>(UPDATED, _iProductService.findProductById(dtoSave.getId()));
        return ResponseEntity.ok(_iProductService.findProductById(dtoSave.getId()));
    }

    @PutMapping(value = "/{id}/status")
    public ResponseEntity<?> changeStatusProduct(@PathVariable("id") String id, Boolean status) {
        ProductDto dtoProduct = _iProductService.findProductById(id);
        if (dtoProduct == null)
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "product", "product.not.found"));
        dtoProduct.setStatus(status);
//        ResultDto<ProductDto> result = new ResultDto<>(UPDATED, _iProductService.findProductById(dtoSave.getId()));
        return ResponseEntity.ok(_iProductService.updateProduct(dtoProduct));
    }

    private ResultSearchDto<Set<ProductDto>> search(Set<ProductDto> lstFake, List<ProductDto> lst, SearchProductVo obj, String[] type, Integer index, Boolean isNull) {
        String[] arrFake = new String[0];
        if (type != null) arrFake = type;
        for (String x : arrFake) {
            if (!x.isEmpty() && !x.isBlank()) {
                isNull = false;
                switch (index) {
                    case 0:
                        lst.forEach(e -> {
                            if (e.getName().toLowerCase().contains(x.toLowerCase())) lstFake.add(e);
                        });
                        break;
                    case 1:
                        lst.forEach(e -> {
                            if (e.getIdBrand().toLowerCase().contains(x.toLowerCase())) lstFake.add(e);
                        });
                        break;
                    case 2:
                        lst.forEach(e -> {
                            if (e.getNameBrand().toLowerCase().contains(x.toLowerCase())) lstFake.add(e);
                        });
                        break;
                    case 3:
                        lst.forEach(e -> {
                            if (e.getIdCategory().toLowerCase().contains(x.toLowerCase())) lstFake.add(e);
                        });
                        break;
                    case 4:
                        lst.forEach(e -> {
                            if (e.getNameCategory().toLowerCase().contains(x.toLowerCase())) lstFake.add(e);
                        });
                        break;
                    case 5:
                        lst.forEach(e -> {
                            if (e.getPrice().toString().toLowerCase().contains(x.toLowerCase())) lstFake.add(e);
                        });
                        break;
                    case 6:
                        lst.forEach(e -> {
                            if (e.getPriceSale().toString().toLowerCase().contains(x.toLowerCase())) lstFake.add(e);
                        });
                        break;
                    case 7:
                        lst.forEach(e -> {
                            if (e.getQuantity().toString().toLowerCase().contains(x.toLowerCase())) lstFake.add(e);
                        });
                        break;
                    case 8:
                        lst.forEach(e -> {
                            if (e.getColor().toLowerCase().toLowerCase().contains(x.toLowerCase())) lstFake.add(e);
                        });
                        break;
                    case 9:
                        lst.forEach(e -> {
                            if (e.getCategoryDetails().toLowerCase().contains(x.toLowerCase())) lstFake.add(e);
                        });
                        break;
                    case 10:
                        lst.forEach(e -> {
                            if (e.getLocation().toLowerCase().contains(x.toLowerCase())) lstFake.add(e);
                        });
                        break;
                    case 11:
                        lst.forEach(e -> {
                            if (e.getPath().toLowerCase().contains(x.toLowerCase())) lstFake.add(e);
                        });
                        break;
                    case 12:
                        lst.forEach(e -> {
                            if (e.getIdPath().toString().toLowerCase().contains(x.toLowerCase())) lstFake.add(e);
                        });
                        break;
                    case 13:
                        lst.forEach(e -> {
                            if (e.getPathUserManual().toLowerCase().contains(x.toLowerCase())) lstFake.add(e);
                        });
                        break;
                    case 14:
                        lst.forEach(e -> {
                            if (e.getDescription().toLowerCase().contains(x.toLowerCase())) lstFake.add(e);
                        });
                        break;
                    case 15:
                        lst.forEach(e -> {
                            if (e.getStatus().toString().toLowerCase().contains(x.toLowerCase())) lstFake.add(e);
                        });
                        break;
                    case 16:
                        lst.forEach(e -> {
                            if (ConvertUtils.get().dateToString(e.getDateCreated()).contains(x.toLowerCase()))
                                lstFake.add(e);
                        });
                }
            }
        }
        switch (index) {
            case 0:
                return search(lstFake, lst, obj, obj.getIdBrand(), 1, isNull);
            case 1:
                return search(lstFake, lst, obj, obj.getNameBrand(), 2, isNull);
            case 2:
                return search(lstFake, lst, obj, obj.getIdCategory(), 3, isNull);
            case 3:
                return search(lstFake, lst, obj, obj.getNameCategory(), 4, isNull);
            case 4:
                return search(lstFake, lst, obj, obj.getPrice(), 5, isNull);
            case 5:
                return search(lstFake, lst, obj, obj.getPriceSale(), 6, isNull);
            case 6:
                return search(lstFake, lst, obj, obj.getQuantity(), 7, isNull);
            case 7:
                return search(lstFake, lst, obj, obj.getColor(), 8, isNull);
            case 8:
                return search(lstFake, lst, obj, obj.getCategoryDetails(), 9, isNull);
            case 9:
                return search(lstFake, lst, obj, obj.getLocation(), 10, isNull);
            case 10:
                return search(lstFake, lst, obj, obj.getPath(), 11, isNull);
            case 11:
                return search(lstFake, lst, obj, obj.getIdPath(), 12, isNull);
            case 12:
                return search(lstFake, lst, obj, obj.getPathUserManual(), 13, isNull);
            case 13:
                return search(lstFake, lst, obj, obj.getDescription(), 14, isNull);
            case 14:
                return search(lstFake, lst, obj, obj.getStatus(), 15, isNull);
            case 15:
                return search(lstFake, lst, obj, obj.getDateCreated(), 16, isNull);
            default:
                return new ResultSearchDto<>(isNull, lstFake);
        }
    }
}


