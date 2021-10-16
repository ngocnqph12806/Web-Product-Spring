package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.CategoryDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.ICategoryService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.SearchCategoryVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryApi extends AbstractApi {

    private final ICategoryService _iCategoryService;

    protected CategoryApi(HttpServletRequest request, HttpServletResponse response, ICategoryService iCategoryService) {
        super(request, response);
        _iCategoryService = iCategoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(SearchCategoryVo searchCategoryVo) {
        List<CategoryDto> lst = _iCategoryService.findAll();
        lst = search(lst, searchCategoryVo, searchCategoryVo.getName(), 0);
        return ResponseEntity.ok(lst);
    }

    @GetMapping(params = "nav")
    public ResponseEntity<?> getForNav() {
        List<CategoryDto> lst = _iCategoryService.findAll();
        Set<String> lstStr = lst.stream().map(CategoryDto::getName).collect(Collectors.toSet());
        return ResponseEntity.ok(lstStr);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(_iCategoryService.findById(id));
    }

    @GetMapping(value = "/{id}", params = "modal")
    public ResponseEntity<?> getByIdWithModal(@PathVariable("id") String id) {
        ResultDto<CategoryDto> result = new ResultDto<>(OK, null);
        try {
            return ResponseEntity.ok(_iCategoryService.findById(id));
        } catch (Exception e) {
        }
        return ResponseEntity.ok(new CategoryDto());
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody CategoryDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "category", errors.getFieldErrors().get(0).getDefaultMessage()));
        return ResponseEntity.ok(_iCategoryService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @Validated @RequestBody CategoryDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "category", errors.getFieldErrors().get(0).getDefaultMessage()));
        }
        if (!dto.getId().equals(id))
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        return ResponseEntity.ok(_iCategoryService.update(dto));
    }

    private List<CategoryDto> search(List<CategoryDto> lst, SearchCategoryVo obj, String[] type, Integer index) {
        String[] arrFake = new String[0];
        if (type != null) arrFake = type;
        for (String x : arrFake) {
            switch (index) {
                case 0:
                    lst = lst.stream().filter(e -> e.getName().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 1:
                    lst = lst.stream().filter(e -> e.getBanner().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 2:
                    lst = lst.stream().filter(e -> e.getPath().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 3:
                    lst = lst.stream().filter(e -> e.getDescription().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 4:
                    lst = lst.stream().filter(e -> e.getStatus().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 5:
                    lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateCreated()).contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 6:
                    lst = lst.stream().filter(e -> e.getCountProducts().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
            }
        }
        switch (index) {
            case 0:
                return search(lst, obj, obj.getBanner(), 1);
            case 1:
                return search(lst, obj, obj.getPath(), 2);
            case 2:
                return search(lst, obj, obj.getDescription(), 3);
            case 3:
                return search(lst, obj, obj.getStatus(), 4);
            case 4:
                return search(lst, obj, obj.getDateCreated(), 5);
            case 5:
                return search(lst, obj, obj.getCountProducts(), 6);
            default:
                return lst;
        }
    }

}
