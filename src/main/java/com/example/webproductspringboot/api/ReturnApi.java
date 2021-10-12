package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.service.intf.ICustomersReturnService;
import com.example.webproductspringboot.service.intf.IOrderService;
import com.example.webproductspringboot.service.intf.IProductService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.ReturnDetailDto;
import com.example.webproductspringboot.vo.SearchReturnVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/returns")
public class ReturnApi extends AbstractApi {

    private final ICustomersReturnService _iCustomersReturnService;
    private final IOrderService _iOrderService;
    private final IProductService _iProductService;

    protected ReturnApi(HttpServletRequest request, ICustomersReturnService iCustomersReturnService, IOrderService iOrderService, IProductService iProductService) {
        super(request);
        _iCustomersReturnService = iCustomersReturnService;
        _iOrderService = iOrderService;
        _iProductService = iProductService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(SearchReturnVo searchReturnVo) {
        List<ReturnDto> lst = _iCustomersReturnService.findAll();
        lst = search(lst, searchReturnVo, searchReturnVo.getIdOrder(), 0);
        ResultDto<List<ReturnDto>> result = new ResultDto<>(OK, lst);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new ResultDto<>(OK, _iCustomersReturnService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ReturnDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "return", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (dto.getDetails() == null || dto.getDetails().isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "return", "return.not.found"));
        if (dto.getDetails().stream().mapToInt(ReturnDetailDto::getQuantity).sum() <= 0)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "return", "not.product.return")); // không có sản phẩm nào được trả lại
        OrderDto orderDtoFindById = _iOrderService.findById(dto.getIdOrder());
        for (OrderDetailDto x : orderDtoFindById.getDetails()) {
            for (ReturnDetailDto s : dto.getDetails()) {
                if (x.getId().equals(s.getIdOrderDetail()) && (x.getQuantity() - x.getQuantityReturn()) < s.getQuantity())
                    throw new BadRequestException(CookieUtils.get().errorsProperties(request, "return", "quantity.return.not.enough"));
            }
        }
        ReturnDto returnDtoSave = _iCustomersReturnService.save(dto);
        if (returnDtoSave != null) {
            try {
                saveReturnDetailWithUpdateProduct(dto, returnDtoSave);
            } catch (Exception e) {
                _iCustomersReturnService.removeReturnDetailByIdReturn(returnDtoSave.getId());
                _iCustomersReturnService.removeReturn(returnDtoSave);
                return ResponseEntity.ok(null);
            }
        }
        return ResponseEntity.ok(returnDtoSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid ReturnDto dto, Errors errors) {
        ReturnDto returnDtoSession = (ReturnDto) request.getSession().getAttribute("session_dto_return");
        System.out.println(dto);
        if (returnDtoSession == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "return", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (!dto.getId().equals(id))
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        if (dto.getDetails() == null || dto.getDetails().isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "return", "return.not.found"));
        if (dto.getDetails().stream().mapToInt(ReturnDetailDto::getQuantity).sum() <= 0)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "return", "not.product.return")); // không có sản phẩm nào được trả lại
        OrderDto orderDtoFindById = _iOrderService.findById(dto.getIdOrder());
        for (OrderDetailDto x : orderDtoFindById.getDetails()) {
            for (ReturnDetailDto s : dto.getDetails()) {
                for (ReturnDetailDto m : returnDtoSession.getDetails()) {
                    if (x.getId().equals(s.getIdOrderDetail())
                            && x.getId().equals(m.getIdOrderDetail())
                            && s.getIdOrderDetail().equals(m.getIdOrderDetail())
                            && (x.getQuantity() - x.getQuantityReturn() + m.getQuantity()) < s.getQuantity())
                        throw new BadRequestException(CookieUtils.get().errorsProperties(request, "return", "quantity.return.not.enough"));
                }
            }
        }
        ReturnDto returnDtoFindById = _iCustomersReturnService.findById(id);
        ReturnDto returnDtoSave = _iCustomersReturnService.update(dto);
        if (returnDtoSave != null) {
            saveReturnDetailWithUpdateProduct(dto, returnDtoSave);
            for (ReturnDetailDto x : returnDtoFindById.getDetails()) {
                _iCustomersReturnService.removeReturnDetailById(x.getId());
                OrderDetailDto orderDetailDto = _iOrderService.findOrderDetailById(x.getIdOrderDetail());
                ProductDto productDto = _iProductService.findProductById(orderDetailDto.getIdProduct());
                productDto.setQuantity(productDto.getQuantity() - x.getQuantity());
                _iProductService.updateProduct(productDto);
            }
        }
        return ResponseEntity.ok(returnDtoSave);
    }

    private void saveReturnDetailWithUpdateProduct(@RequestBody @Valid ReturnDto dto, ReturnDto returnDtoSave) {
        for (ReturnDetailDto x : dto.getDetails()) {
            if (x.getQuantity() > 0) {
                x.setIdReturn(returnDtoSave.getId());
                _iCustomersReturnService.saveReturnDetail(x);
                OrderDetailDto orderDetailDto = _iOrderService.findOrderDetailById(x.getIdOrderDetail());
                ProductDto productDtoFindById = _iProductService.findProductById(orderDetailDto.getIdProduct());
                productDtoFindById.setQuantity(productDtoFindById.getQuantity() + x.getQuantity());
                _iProductService.updateProduct(productDtoFindById);
            }
        }
    }

    private List<ReturnDto> search(List<ReturnDto> lst, SearchReturnVo obj, String[] type, Integer index) {
        String[] arrFake = new String[0];
        if (type != null) arrFake = type;
        for (String x : arrFake) {
            switch (index) {
                case 0:
                    lst = lst.stream().filter(e -> e.getIdOrder().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 1:
                    lst = lst.stream().filter(e -> ConvertUtils.get().dateToString(e.getDateOrder()).contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 2:
//                    lst = lst.stream().filter(e -> e.getIdUser().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 3:
                    lst = lst.stream().filter(e -> e.getNameUser().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 4:
                    lst = lst.stream().filter(e -> e.getIdStaff().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 5:
                    lst = lst.stream().filter(e -> e.getNameStaff().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
                    break;
                case 6:
                    lst = lst.stream().filter(e -> e.getDescription().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
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
                return search(lst, obj, obj.getDateOrder(), 1);
            case 1:
                return search(lst, obj, obj.getIdUser(), 2);
            case 2:
                return search(lst, obj, obj.getNameUser(), 3);
            case 3:
                return search(lst, obj, obj.getIdStaff(), 4);
            case 4:
                return search(lst, obj, obj.getNameStaff(), 5);
            case 5:
                return search(lst, obj, obj.getDescription(), 6);
            case 6:
                return search(lst, obj, obj.getStatus(), 7);
            case 7:
                return search(lst, obj, obj.getDateCreated(), 8);
            default:
                return lst;
        }
    }

}
