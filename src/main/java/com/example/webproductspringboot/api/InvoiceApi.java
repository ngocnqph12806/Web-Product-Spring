package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.InvoiceDto;
import com.example.webproductspringboot.dto.ResultDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.service.intf.IInvoiceService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.SearchBannerVo;
import com.example.webproductspringboot.vo.SearchInvoiceVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceApi extends AbstractApi {

    private final IInvoiceService _iInvoiceService;

    protected InvoiceApi(HttpServletRequest request, IInvoiceService iInvoiceService) {
        super(request);
        _iInvoiceService = iInvoiceService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(SearchInvoiceVo searchInvoiceVo) {
        List<InvoiceDto> lst = _iInvoiceService.findAll();
        lst = search(lst, searchInvoiceVo, searchInvoiceVo.getIdCreator(), 0);
        ResultDto<List<InvoiceDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iInvoiceService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid InvoiceDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "invoice", errors.getFieldErrors().get(0).getDefaultMessage()));
        ResultDto<InvoiceDto> result = new ResultDto<>(CREATED, _iInvoiceService.save(dto));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid InvoiceDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "invoice", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (!dto.getId().equals(id))
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        ResultDto<InvoiceDto> result = new ResultDto<>(UPDATED, _iInvoiceService.update(dto));
        return ResponseEntity.ok(result);
    }

    private List<InvoiceDto> search(List<InvoiceDto> lst, SearchInvoiceVo obj, String[] type, Integer index) {
        String[] arrFake = new String[0];
        if (type != null) arrFake = type;
        for (String x : arrFake) {
            switch (index) {
                case 0:
                    lst = lst.stream().filter(e -> e.getIdCreator().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 1:
                    lst = lst.stream().filter(e -> e.getNameCreator().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 2:
                    lst = lst.stream().filter(e -> e.getIdChecker().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 3:
                    lst = lst.stream().filter(e -> e.getNameChecker().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 4:
                    lst = lst.stream().filter(e -> e.getDescription().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 5:
                    lst = lst.stream().filter(e -> e.getStatus().toString().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 6:
                    lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateCreated()).contains(x.toLowerCase())).collect(Collectors.toList());
            }
        }
        switch (index) {
            case 0:
                return search(lst, obj, obj.getNameCreator(), 1);
            case 1:
                return search(lst, obj, obj.getIdChecker(), 2);
            case 2:
                return search(lst, obj, obj.getNameChecker(), 3);
            case 3:
                return search(lst, obj, obj.getDescription(), 4);
            case 4:
                return search(lst, obj, obj.getStatus(), 5);
            case 5:
                return search(lst, obj, obj.getDateCreated(), 6);
            default:
                return lst;
        }
    }

}
