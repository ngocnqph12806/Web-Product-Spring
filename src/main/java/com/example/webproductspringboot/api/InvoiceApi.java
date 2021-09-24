package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.BannerDto;
import com.example.webproductspringboot.dto.InvoiceDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceApi {

    @Autowired
    private IInvoiceService _iInvoiceService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<InvoiceDto> lst = _iInvoiceService.findAll();
        ResultDto<List<InvoiceDto>> result = new ResultDto<>(true, "", lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(true, "", _iInvoiceService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid InvoiceDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<InvoiceDto> result = new ResultDto<>(true, "Đã thêm mới hoá đơn nhập hàng", _iInvoiceService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid InvoiceDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<InvoiceDto> result = new ResultDto<>(true, "Đã chỉnh sửa hoá đơn nhập hàng", _iInvoiceService.update(dto));
        return ResponseEntity.ok(result);
    }

}
