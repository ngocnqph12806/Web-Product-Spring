package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IOrderService;
import com.example.webproductspringboot.utils.CookieUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderApi extends AbstractApi {

    private final IOrderService _iOrderService;

    protected OrderApi(HttpServletRequest request, IOrderService iOrderService) {
        super(request);
        _iOrderService = iOrderService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<OrderDto> lst = _iOrderService.findAll();
        ResultDto<List<OrderDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iOrderService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid OrderDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<OrderDto> result = new ResultDto<>(CREATED, _iOrderService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid OrderDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        if (!dto.getId().equals(id)) throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ResultDto<OrderDto> result = new ResultDto<>(UPDATED, _iOrderService.update(dto));
        return ResponseEntity.ok(result);
    }

}
