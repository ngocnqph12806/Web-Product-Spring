package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.ICustomersReturnService;
import com.example.webproductspringboot.utils.CookieUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/returns")
public class ReturnApi extends AbstractApi {

    private final ICustomersReturnService _iCustomersReturnService;

    protected ReturnApi(HttpServletRequest request, ICustomersReturnService iCustomersReturnService) {
        super(request);
        _iCustomersReturnService = iCustomersReturnService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ReturnDto> lst = _iCustomersReturnService.findAll();
        ResultDto<List<ReturnDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iCustomersReturnService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ReturnDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        ResultDto<ReturnDto> result = new ResultDto<>(CREATED, _iCustomersReturnService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid ReturnDto dto, Errors errors) {
        if (errors.hasErrors()) throw new BadRequestException(errors.getFieldErrors().get(0).getDefaultMessage());
        if (!dto.getId().equals(id)) throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ResultDto<ReturnDto> result = new ResultDto<>(UPDATED, _iCustomersReturnService.update(dto));
        return ResponseEntity.ok(result);
    }

}
