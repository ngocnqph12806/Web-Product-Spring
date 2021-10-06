package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IOrderService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.SearchBannerVo;
import com.example.webproductspringboot.vo.SearchOrderVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderApi extends AbstractApi {

    private final IOrderService _iOrderService;

    protected OrderApi(HttpServletRequest request, IOrderService iOrderService) {
        super(request);
        _iOrderService = iOrderService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(SearchOrderVo searchOrderVo) {
        List<OrderDto> lst = _iOrderService.findAll();
        lst = search(lst, searchOrderVo, searchOrderVo.getIdUser(), 0);
//        ResultDto<List<OrderDto>> result = new ResultDto<>(OK, lst);
//        lst.add(new OrderDto());
//        lst.add(new OrderDto());
//        lst.add(new OrderDto());
        return ResponseEntity.ok(lst);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iOrderService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid OrderDto dto, Errors errors) {
        System.out.println(dto);
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "order", errors.getFieldErrors().get(0).getDefaultMessage()));
        ResultDto<OrderDto> result = new ResultDto<>(CREATED, _iOrderService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid OrderDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "order", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (!dto.getId().equals(id))
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ResultDto<OrderDto> result = new ResultDto<>(UPDATED, _iOrderService.update(dto));
        return ResponseEntity.ok(result);
    }

    private List<OrderDto> search(List<OrderDto> lst, SearchOrderVo obj, String[] type, Integer index) {
        String[] arrFake = new String[0];
        if (type != null) arrFake = type;
        for (String x : arrFake) {
            switch (index) {
                case 0:
                    lst = lst.stream().filter(e -> e.getIdUser().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 1:
                    lst = lst.stream().filter(e -> e.getNameUser().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 2:
                    lst = lst.stream().filter(e -> e.getIdCreator().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 3:
                    lst = lst.stream().filter(e -> e.getNameCreator().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 4:
                    lst = lst.stream().filter(e -> e.getIdSaller().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 5:
                    lst = lst.stream().filter(e -> e.getNameSaller().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 6:
                    lst = lst.stream().filter(e -> e.getPaymentMethod().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 7:
                    lst = lst.stream().filter(e -> e.getPaymentStatus().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 8:
                    lst = lst.stream().filter(e -> e.getDescription().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 9:
                    lst = lst.stream().filter(e -> e.getStatus().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 10:
                    lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateCreated()).contains(x.toLowerCase())).collect(Collectors.toList());
            }
        }
        switch (index) {
            case 0:
                return search(lst, obj, obj.getNameUser(), 1);
            case 1:
                return search(lst, obj, obj.getIdCreator(), 2);
            case 2:
                return search(lst, obj, obj.getNameCreator(), 3);
            case 3:
                return search(lst, obj, obj.getIdSaller(), 4);
            case 4:
                return search(lst, obj, obj.getNameSaller(), 5);
            case 5:
                return search(lst, obj, obj.getPaymentMethod(), 6);
            case 6:
                return search(lst, obj, obj.getPaymentStatus(), 7);
            case 7:
                return search(lst, obj, obj.getDescription(), 8);
            case 8:
                return search(lst, obj, obj.getStatus(), 9);
            case 9:
                return search(lst, obj, obj.getDateCreated(), 10);
            default:
                return lst;
        }
    }

}
