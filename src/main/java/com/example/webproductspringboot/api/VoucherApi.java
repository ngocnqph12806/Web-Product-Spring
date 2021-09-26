package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IVoucherService;
import com.example.webproductspringboot.utils.CookieUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/vouchers")
public class VoucherApi extends AbstractApi {

    private final IVoucherService _iVoucherService;

    protected VoucherApi(HttpServletRequest request, IVoucherService iVoucherService) {
        super(request);
        _iVoucherService = iVoucherService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<VoucherDto> lst = _iVoucherService.findAll();
        ResultDto<List<VoucherDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iVoucherService.findById(id)));
    }

    @GetMapping(value = "/{id}", params = "modal")
    public ResponseEntity<?> getByIdWithModal(@PathVariable("id") String id) {
        ResultDto<VoucherDto> result = new ResultDto<>(OK, null);
        try {
            result.setData(_iVoucherService.findById(id));
        } catch (Exception e) {
            result.setData(new VoucherDto());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody VoucherDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ResultDto<VoucherDto> result = new ResultDto<>(CREATED, _iVoucherService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @Validated @RequestBody VoucherDto dto, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        if (!dto.getId().equals(id)) throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ResultDto<VoucherDto> result = new ResultDto<>(UPDATED, _iVoucherService.update(dto));
        return ResponseEntity.ok(result);
    }

}
