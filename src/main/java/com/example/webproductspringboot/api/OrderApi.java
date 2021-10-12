package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.service.intf.IOrderService;
import com.example.webproductspringboot.service.intf.IProductService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
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
    private final IProductService _iProductService;

    protected OrderApi(HttpServletRequest request, IOrderService iOrderService, IProductService iProductService) {
        super(request);
        _iOrderService = iOrderService;
        _iProductService = iProductService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(SearchOrderVo searchOrderVo) {
        List<OrderDto> lst = _iOrderService.findAll();
        lst = search(lst, searchOrderVo, searchOrderVo.getIdUser(), 0);
        return ResponseEntity.ok(lst);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(_iOrderService.findById(id));
    }

    @GetMapping(value = "/get-by-user-login", params = "id")
    public ResponseEntity<?> getAllOrderByUserLogin(@RequestParam("id") String id) {
        return ResponseEntity.ok(_iOrderService.getAllOrderByUserLogin(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid OrderDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "order", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (dto.getDetails() == null || dto.getDetails().isEmpty())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "order", "details.order.not.found"));
        OrderDto dtoSave = _iOrderService.save(dto);
        if (dtoSave != null) {
            saveDetailOrderWithUpdateQuantityProduct(dto, dtoSave);
        }
        return ResponseEntity.ok(dtoSave);
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> saveCheckout(@RequestBody @Valid ChechoutDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "order", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (dto.getDetails() == null || dto.getDetails().isEmpty())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "order", "details.order.not.found"));
        OrderDto dtoSave = _iOrderService.saveCheckout(dto);
        if (dtoSave != null) {
            for (OrderDetailDto x : dto.getDetails()) {
                x.setIdOrder(dtoSave.getId());
                try {
                    ProductDto productDto = _iProductService.findProductById(x.getIdProduct());
                    productDto.setQuantity(productDto.getQuantity() - x.getQuantity());
                    x.setPrice(productDto.getPrice());
                    _iOrderService.saveDetailOrder(x);
                    _iProductService.updateProduct(productDto);
                } catch (Exception e) {
                    _iOrderService.removeOrder(dtoSave);
                    throw new BadRequestException(e.getMessage());
                }
            }
        }
        return ResponseEntity.ok(dtoSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody @Valid OrderDto dto, Errors errors) {
        if (errors.hasErrors())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "order", errors.getFieldErrors().get(0).getDefaultMessage()));
        if (!dto.getId().equals(id))
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "id.not.equal.dto"));
        if (dto.getDetails() == null || dto.getDetails().isEmpty())
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "order", "details.order.not.found"));
        OrderDto dtoFindById = _iOrderService.findById(id);
        OrderDto dtoSave = _iOrderService.update(dto);
        if (dtoSave != null) {
            saveDetailOrderWithUpdateQuantityProduct(dto, dtoSave);
            for (OrderDetailDto x : dtoFindById.getDetails()) {
                _iOrderService.removeDetailOrderById(x.getId());
                ProductDto productDto = _iProductService.findProductById(x.getIdProduct());
                productDto.setQuantity(productDto.getQuantity() + x.getQuantity());
                _iProductService.updateProduct(productDto);
            }
        }
        return ResponseEntity.ok(dtoSave);
    }

    @PutMapping("/{id}/{method}")
    public ResponseEntity<?> updateStatusOrder(@PathVariable("id") String id, @PathVariable("method") String method, Boolean status) {
        OrderDto dtoFindById = _iOrderService.findById(id);
        if (dtoFindById == null)
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "order", "order.not.found"));
        if (method.equals("status")) {
            dtoFindById.setStatus(status);
            if (_iOrderService.update(dtoFindById) != null) {
                for (OrderDetailDto x : dtoFindById.getDetails()) {
                    ProductDto productDto = _iProductService.findProductById(x.getIdProduct());
                    if (status) {
                        productDto.setQuantity(productDto.getQuantity() + x.getQuantity());
                    } else {
                        productDto.setQuantity(productDto.getQuantity() - x.getQuantity());
                    }
                    _iProductService.updateProduct(productDto);
                }
            }
        } else if (method.equals("payment-status")) {
            dtoFindById.setPaymentStatus(status);
            _iOrderService.update(dtoFindById);
        }
        return ResponseEntity.ok(dtoFindById);
    }

    private void saveDetailOrderWithUpdateQuantityProduct(OrderDto dto, OrderDto dtoSave) {
        for (OrderDetailDto x : dto.getDetails()) {
            x.setIdOrder(dtoSave.getId());
            try {
                ProductDto productDto = _iProductService.findProductById(x.getIdProduct());
                productDto.setQuantity(productDto.getQuantity() - x.getQuantity());
                x.setPrice(productDto.getPrice());
                _iOrderService.saveDetailOrder(x);
                _iProductService.updateProduct(productDto);
            } catch (Exception e) {
                _iOrderService.removeOrder(dtoSave);
                throw new BadRequestException(e.getMessage());
            }
        }
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
