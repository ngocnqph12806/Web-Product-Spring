package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IReviewProductService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.SearchBannerVo;
import com.example.webproductspringboot.vo.SearchReturnVo;
import com.example.webproductspringboot.vo.SearchReviewVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
public class ReviewApi extends AbstractApi {

    private final IReviewProductService _iReviewProductService;

    protected ReviewApi(HttpServletRequest request, IReviewProductService iReviewProductService) {
        super(request);
        _iReviewProductService = iReviewProductService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(SearchReviewVo searchReviewVo) {
        List<ReviewDto> lst = _iReviewProductService.findAll();
        lst = search(lst, searchReviewVo, searchReviewVo.getIdProduct(), 0);
        ResultDto<List<ReviewDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
//        return ResponseEntity.ok(new ResultDto<>(HttpCodeApi.OK, _iReviewProductService.findById(id)));
        return null;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ReviewDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "review", errors.getFieldErrors().get(0).getDefaultMessage()));
        ResultDto<ReviewDto> result = new ResultDto<>(CREATED, _iReviewProductService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid ReviewDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "review", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (!dto.getId().equals(id))
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ResultDto<ReviewDto> result = new ResultDto<>(UPDATED, _iReviewProductService.update(dto));
        return ResponseEntity.ok(result);
    }

    private List<ReviewDto> search(List<ReviewDto> lst, SearchReviewVo obj, String[] type, Integer index) {
        String[] arrFake = new String[0];
        if (type != null) arrFake = type;
        for (String x : arrFake) {
            switch (index) {
                case 0:
                    lst = lst.stream().filter(e -> e.getIdProduct().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 1:
                    lst = lst.stream().filter(e -> e.getNameProduct().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 2:
                    lst = lst.stream().filter(e -> e.getIdUser().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 3:
                    lst = lst.stream().filter(e -> e.getNameUser().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 4:
                    lst = lst.stream().filter(e -> e.getPoint().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 5:
                    lst = lst.stream().filter(e -> e.getDescription().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 6:
                    lst = lst.stream().filter(e -> e.getIntroduce().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 7:
                    lst = lst.stream().filter(e -> e.getStatus().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 8:
                    lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateCreated()).contains(x.toLowerCase())).collect(Collectors.toList());
            }
        }
        switch (index) {
            case 0:
                return search(lst, obj, obj.getNameProduct(), 1);
            case 1:
                return search(lst, obj, obj.getIdUser(), 2);
            case 2:
                return search(lst, obj, obj.getNameUser(), 3);
            case 3:
                return search(lst, obj, obj.getPoint(), 4);
            case 4:
                return search(lst, obj, obj.getDescription(), 5);
            case 5:
                return search(lst, obj, obj.getIntroduce(), 6);
            case 6:
                return search(lst, obj, obj.getStatus(), 7);
            case 7:
                return search(lst, obj, obj.getDateCreated(), 8);
            default:
                return lst;
        }
    }

}
