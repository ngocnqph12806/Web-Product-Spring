package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.InvoiceDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IInvoiceService;
import com.example.webproductspringboot.utils.CookieUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceApi extends AbstractApi {

    private final IInvoiceService _iInvoiceService;

    protected InvoiceApi(HttpServletRequest request, IInvoiceService iInvoiceService) {
        super(request);
        _iInvoiceService = iInvoiceService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<InvoiceDto> lst = _iInvoiceService.findAll();
        ResultDto<List<InvoiceDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iInvoiceService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid InvoiceDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<InvoiceDto> result = new ResultDto<>(CREATED, _iInvoiceService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid InvoiceDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        if (!dto.getId().equals(id)) throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ResultDto<InvoiceDto> result = new ResultDto<>(UPDATED, _iInvoiceService.update(dto));
        return ResponseEntity.ok(result);
    }

}
