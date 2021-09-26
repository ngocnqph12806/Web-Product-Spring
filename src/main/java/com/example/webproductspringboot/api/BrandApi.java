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
        lst = search(lst, searchBrandVo, searchBrandVo.getName());
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
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<BrandDto> result = new ResultDto<>(CREATED, _iBrandService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @Validated @RequestBody BrandDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        if (!dto.getId().equals(id))
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ResultDto<BrandDto> result = new ResultDto<>(UPDATED, _iBrandService.update(dto));
        return ResponseEntity.ok(result);
    }

    private List<BrandDto> search(List<BrandDto> lst, SearchBrandVo searchBrandVo, String[] type) {
        String[] arrFake = new String[0];
        if (type != null) arrFake = type;
        System.out.println(type);
        System.out.println(arrFake);
        for (String x : arrFake) {
            if (lst.isEmpty()) return lst;
            if (Arrays.equals(type, searchBrandVo.getName()))
                lst = lst.stream().filter(e -> e.getName().contains(x))
                        .collect(Collectors.toList());
            else if (Arrays.equals(type, searchBrandVo.getPhoneNumber()))
                lst = lst.stream().filter(e -> e.getPhoneNumber().contains(x))
                        .collect(Collectors.toList());
            else if (Arrays.equals(type, searchBrandVo.getEmail()))
                lst = lst.stream().filter(e -> e.getEmail().contains(x))
                        .collect(Collectors.toList());
            else if (Arrays.equals(type, searchBrandVo.getAddress()))
                lst = lst.stream().filter(e -> e.getAddress().contains(x))
                        .collect(Collectors.toList());
            else if (Arrays.equals(type, searchBrandVo.getDescription()))
                lst = lst.stream().filter(e -> e.getDescription().contains(x))
                        .collect(Collectors.toList());
            else if (Arrays.equals(type, searchBrandVo.getStatus()))
                lst = lst.stream().filter(e -> e.getStatus() == Boolean.parseBoolean(x))
                        .collect(Collectors.toList());
            else if (Arrays.equals(type, searchBrandVo.getDateCreated()))
                lst = lst.stream().filter(e -> ConvertUtils.get()
                        .dateToString(e.getDateCreated()).contains(x))
                        .collect(Collectors.toList());
            else if (Arrays.equals(type, searchBrandVo.getCountProducts()))
                lst = lst.stream().filter(e -> e.getCountProducts() == Integer.parseInt(x))
                        .collect(Collectors.toList());
        }
        if (Arrays.equals(type, searchBrandVo.getName()))
            return search(lst, searchBrandVo, searchBrandVo.getPhoneNumber());
        else if (Arrays.equals(type, searchBrandVo.getPhoneNumber()))
            return search(lst, searchBrandVo, searchBrandVo.getEmail());
        else if (Arrays.equals(type, searchBrandVo.getEmail()))
            return search(lst, searchBrandVo, searchBrandVo.getAddress());
        else if (Arrays.equals(type, searchBrandVo.getAddress()))
            return search(lst, searchBrandVo, searchBrandVo.getDescription());
        else if (Arrays.equals(type, searchBrandVo.getDescription()))
            return search(lst, searchBrandVo, searchBrandVo.getStatus());
        else if (Arrays.equals(type, searchBrandVo.getStatus()))
            return search(lst, searchBrandVo, searchBrandVo.getDateCreated());
        else if (Arrays.equals(type, searchBrandVo.getDateCreated()))
            return search(lst, searchBrandVo, searchBrandVo.getCountProducts());
        else return lst;
    }

}
