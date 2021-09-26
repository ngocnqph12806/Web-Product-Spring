package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.BrandDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IBrandService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.SearchBrandVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/brand")
public class BrandApi extends AbstractApi {

    private final IBrandService _iBrandService;

    protected BrandApi(HttpServletRequest request, IBrandService iBrandService) {
        super(request);
        _iBrandService = iBrandService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(SearchBrandVo searchBrandVo) {
        List<BrandDto> lst = _iBrandService.findAll();
        System.out.println(searchBrandVo);
        lst = search(lst, searchBrandVo, searchBrandVo.getName(), 0);
        ResultDto<List<BrandDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iBrandService.findById(id)));
    }

    @GetMapping(value = "/{id}", params = "modal")
    public ResponseEntity<?> getByIdWithModal(@PathVariable("id") String id) {
        ResultDto<BrandDto> result = new ResultDto<>(OK, null);
        try {
            result.setData(_iBrandService.findById(id));
        } catch (Exception e) {
            result.setData(new BrandDto());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody BrandDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "brand", errors.getFieldErrors().get(0).getDefaultMessage()));
        ResultDto<BrandDto> result = new ResultDto<>(CREATED, _iBrandService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @Validated @RequestBody BrandDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "brand", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (!dto.getId().equals(id))
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ResultDto<BrandDto> result = new ResultDto<>(UPDATED, _iBrandService.update(dto));
        return ResponseEntity.ok(result);
    }

    private List<BrandDto> search(List<BrandDto> lst, SearchBrandVo searchBrandVo, String[] type, Integer index) {
        String[] arrFake = new String[0];
        if (type != null) arrFake = type;
        for (String x : arrFake) {
            if (lst.isEmpty()) return lst;
            switch (index) {
                case 0:
                    lst = lst.stream().filter(e -> e.getName().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 1:
                    lst = lst.stream().filter(e -> e.getPhoneNumber().contains(x)).collect(Collectors.toList());
                    break;
                case 2:
                    lst = lst.stream().filter(e -> e.getEmail().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 3:
                    lst = lst.stream().filter(e -> e.getAddress().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 4:
                    lst = lst.stream().filter(e -> e.getDescription().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 5:
                    lst = lst.stream().filter(e -> e.getStatus().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 6:
                    lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateCreated()).contains(x)).collect(Collectors.toList());
                    break;
                case 7:
                    lst = lst.stream().filter(e -> e.getCountProducts().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
            }
        }
        switch (index) {
            case 0:
                return search(lst, searchBrandVo, searchBrandVo.getPhoneNumber(), 1);
            case 1:
                return search(lst, searchBrandVo, searchBrandVo.getEmail(), 2);
            case 2:
                return search(lst, searchBrandVo, searchBrandVo.getAddress(), 3);
            case 3:
                return search(lst, searchBrandVo, searchBrandVo.getDescription(), 4);
            case 4:
                return search(lst, searchBrandVo, searchBrandVo.getStatus(), 5);
            case 5:
                return search(lst, searchBrandVo, searchBrandVo.getDateCreated(), 6);
            case 6:
                return search(lst, searchBrandVo, searchBrandVo.getCountProducts(), 7);
            default:
                return lst;
        }
    }

}
