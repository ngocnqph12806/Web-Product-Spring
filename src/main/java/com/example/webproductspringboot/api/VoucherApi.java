package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.service.intf.ISendmailService;
import com.example.webproductspringboot.service.intf.IUserService;
import com.example.webproductspringboot.service.intf.IVoucherService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.SearchVoucherVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vouchers")
public class VoucherApi extends AbstractApi {

    private final IVoucherService _iVoucherService;
    private final ISendmailService _iSendmailService;
    private final IUserService _iUserService;

    protected VoucherApi(HttpServletRequest request, HttpServletResponse response, IVoucherService iVoucherService, ISendmailService iSendmailService, IUserService iUserService) {
        super(request, response);
        _iVoucherService = iVoucherService;
        _iSendmailService = iSendmailService;
        _iUserService = iUserService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(SearchVoucherVo searchVoucherVo) {
        List<VoucherDto> lst = _iVoucherService.findAll();
        lst = search(lst, searchVoucherVo, searchVoucherVo.getCode(), 0);
//        ResultDto<List<VoucherDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(lst);
    }

    @GetMapping(params = "code")
    public ResponseEntity<?> getByCode(@RequestParam("code") String code) {
        return ResponseEntity.ok(_iVoucherService.findByCode(code));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(_iVoucherService.findById(id));
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
    public ResponseEntity<?> save(@Validated @RequestBody VoucherDto voucher, Errors errors) {
        System.out.println(voucher);
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "voucher", errors.getFieldErrors().get(0).getDefaultMessage()));
        VoucherDto result = _iVoucherService.save(voucher);
        List<String> getEmailUsers = _iUserService.getAllEmail();
        String message = "<h3>Mã giảm giá: " + result.getCode() + "</h3>\n";
        message += "<h3>Giá trị: " + result.getPriceSale() + "</h3>\n";
        message += "<h3>Ngày bắt đầu: " + result.getDateStart() + "</h3>\n";
        message += "<h3>Ngày kết thúc: " + result.getDateEnd() + "</h3>\n";
        message += result.getDescription();
        _iSendmailService.pushBcc(getEmailUsers, result.getTitle(), message);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @Validated @RequestBody VoucherDto voucher, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "voucher", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (!voucher.getId().equals(id))
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ResultDto<VoucherDto> result = new ResultDto<>(UPDATED, _iVoucherService.update(voucher));
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/{id}/{status}")
    public ResponseEntity<?> changeStatusProduct(@PathVariable("id") String id, Boolean status) {
        System.out.println(status);
        VoucherDto dtoVoucher = _iVoucherService.findById(id);
        if (dtoVoucher == null)
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "voucher", "voucher.not.found"));
        dtoVoucher.setStatus(status);
        return ResponseEntity.ok(_iVoucherService.update(dtoVoucher));
    }

    private List<VoucherDto> search(List<VoucherDto> lst, SearchVoucherVo obj, String[] type, Integer index) {
        String[] arrFake = new String[0];
        if (type != null) arrFake = type;
        for (String x : arrFake) {
            switch (index) {
                case 0:
                    lst = lst.stream().filter(e -> e.getCode().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 1:
                    lst = lst.stream().filter(e -> e.getTitle().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 2:
                    lst = lst.stream().filter(e -> e.getIdStaff().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 3:
                    lst = lst.stream().filter(e -> e.getNameStaff().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 4:
                    lst = lst.stream().filter(e -> e.getQuantity().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 5:
                    lst = lst.stream().filter(e -> e.getPriceSale().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 6:
                    lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateStart()).contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 7:
                    lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateEnd()).contains(x.toLowerCase())).collect(Collectors.toList());
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
                return search(lst, obj, obj.getTitle(), index + 1);
            case 1:
                return search(lst, obj, obj.getIdStaff(), index + 1);
            case 2:
                return search(lst, obj, obj.getNameStaff(), index + 1);
            case 3:
                return search(lst, obj, obj.getQuantity(), index + 1);
            case 4:
                return search(lst, obj, obj.getPriceSale(), index + 1);
            case 5:
                return search(lst, obj, obj.getDateStart(), index + 1);
            case 6:
                return search(lst, obj, obj.getDateEnd(), index + 1);
            case 7:
                return search(lst, obj, obj.getDescription(), index + 1);
            case 8:
                return search(lst, obj, obj.getStatus(), index + 1);
            case 9:
                return search(lst, obj, obj.getDateCreated(), index + 1);
            default:
                return lst;
        }
    }

}
