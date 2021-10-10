package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.InvoiceDetailDto;
import com.example.webproductspringboot.dto.InvoiceDto;
import com.example.webproductspringboot.dto.ProductDto;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.service.intf.IInvoiceService;
import com.example.webproductspringboot.service.intf.IProductService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.SearchInvoiceVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceApi extends AbstractApi {

    private final IInvoiceService _iInvoiceService;
    private final IProductService _iProductService;

    protected InvoiceApi(HttpServletRequest request, IInvoiceService iInvoiceService, IProductService iProductService) {
        super(request);
        _iInvoiceService = iInvoiceService;
        _iProductService = iProductService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(SearchInvoiceVo searchInvoiceVo) {
        List<InvoiceDto> lst = _iInvoiceService.findAll();
        lst = search(lst, searchInvoiceVo, searchInvoiceVo.getIdCreator(), 0);
        return ResponseEntity.ok(lst);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(_iInvoiceService.findById(id));
    }

    @GetMapping(value = "/{id}", params = "modal")
    public ResponseEntity<?> getByIdWithModal(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(_iInvoiceService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(new InvoiceDto());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid InvoiceDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "invoice", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (dto.getInvoiceDetails() == null || dto.getInvoiceDetails().isEmpty())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "invoice", "details.invoice.not.found"));
        InvoiceDto dtoFake = _iInvoiceService.save(dto);
        if (dtoFake != null) {
            saveDetailInvoiceWithUpdateQuantityProduct(dto, dtoFake);
            return ResponseEntity.ok(dtoFake);
        }
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid InvoiceDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "invoice", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (!dto.getId().equals(id))
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        if (dto.getInvoiceDetails() == null || dto.getInvoiceDetails().isEmpty())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "invoice", "details.invoice.not.found"));
        InvoiceDto dtoFindById = _iInvoiceService.findById(id);
        InvoiceDto dtoSave = _iInvoiceService.update(dto);
        if (dtoSave != null) {
            saveDetailInvoiceWithUpdateQuantityProduct(dto, dtoSave);
            for (InvoiceDetailDto x : dtoFindById.getInvoiceDetails()) {
                _iInvoiceService.removeDetailInvoiceById(x.getId());
                ProductDto productDto = _iProductService.findProductById(x.getIdProduct());
                productDto.setQuantity(productDto.getQuantity() - x.getQuantity());
                _iProductService.updateProduct(productDto);
            }
            return ResponseEntity.ok(dtoSave);
        }
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatusInvoice(@PathVariable("id") String id, Boolean status) {
        InvoiceDto dtoFindById = _iInvoiceService.findById(id);
        if (dtoFindById == null)
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "invoice", "invoice.not.found"));
        dtoFindById.setStatus(status);
        if (_iInvoiceService.update(dtoFindById) != null) {
            for (InvoiceDetailDto x : dtoFindById.getInvoiceDetails()) {
                ProductDto productDto = _iProductService.findProductById(x.getIdProduct());
                if (status) {
                    productDto.setQuantity(productDto.getQuantity() - x.getQuantity());
                } else {
                    productDto.setQuantity(productDto.getQuantity() + x.getQuantity());
                }
                _iProductService.updateProduct(productDto);
            }
        }
        return ResponseEntity.ok(dtoFindById);
    }

    private void saveDetailInvoiceWithUpdateQuantityProduct(@RequestBody @Valid InvoiceDto dto, InvoiceDto dtoSave) {
        for (InvoiceDetailDto x : dto.getInvoiceDetails()) {
            x.setIdInvoice(dtoSave.getId());
            try {
                _iInvoiceService.saveDetailInvoice(x);
                ProductDto productDto = _iProductService.findProductById(x.getIdProduct());
                productDto.setQuantity(productDto.getQuantity() + x.getQuantity());
                _iProductService.updateProduct(productDto);
            } catch (Exception e) {
                _iInvoiceService.removeInvoice(dtoSave);
                throw new BadRequestException(e.getMessage());
            }
        }
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
