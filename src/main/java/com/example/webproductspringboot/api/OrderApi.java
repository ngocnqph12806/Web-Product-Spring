package com.example.webproductspringboot.api;

import com.example.webproductspringboot.config.VnpayConfig;
import com.example.webproductspringboot.dto.*;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.service.intf.IOrderService;
import com.example.webproductspringboot.service.intf.IProductService;
import com.example.webproductspringboot.service.intf.ISendmailService;
import com.example.webproductspringboot.utils.ConvertUtils;
import com.example.webproductspringboot.utils.CookieUtils;
import com.example.webproductspringboot.vo.SearchOrderVo;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderApi extends AbstractApi {

    private final IOrderService _iOrderService;
    private final IProductService _iProductService;
    private final ISendmailService _iSendmailService;

    protected OrderApi(HttpServletRequest request, HttpServletResponse response, IOrderService iOrderService, IProductService iProductService, ISendmailService iSendmailService) {
        super(request, response);
        _iOrderService = iOrderService;
        _iProductService = iProductService;
        _iSendmailService = iSendmailService;
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

    @GetMapping(value = "/payment-success", params = "vnp_TxnRef")
    public void paymentSuccess(@RequestParam("vnp_TxnRef") String idPayment,
                               @RequestParam("vnp_BankTranNo") String vnp_BankTranNo,
                               @RequestParam("vnp_TransactionNo") String vnp_TransactionNo,
                               @RequestParam("vnp_ResponseCode") String vnp_ResponseCode,
                               @RequestParam("vnp_OrderInfo") String vnp_OrderInfo
    ) throws IOException {
//        PaymentVo paymentVo = (PaymentVo) request.getSession().getAttribute("payment_vo");
        if (
//                idPayment != null && !idPayment.isEmpty()
//                && paymentVo.id.equals(idPayment)
//                && paymentVo.url.equals(vnp_OrderInfo) &&
                vnp_BankTranNo != null && vnp_ResponseCode != null && vnp_ResponseCode.equals("00")
                        && vnp_TransactionNo != null && Integer.parseInt(vnp_TransactionNo) > 0) {
            OrderDto orderDtoFindById = _iOrderService.findById(idPayment);
            if (orderDtoFindById != null) {
                _iOrderService.updatePayment(orderDtoFindById.getId());
                response.sendRedirect(vnp_OrderInfo);
            }
        }
        response.sendRedirect(request.getRequestURL().toString() + "/login");
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
        assert dtoSave != null;
        sendMailInfoOrderBill(dtoSave, dto.getDetails(), dto.getEmail());
        return ResponseEntity.ok(dtoSave);
    }

    @PostMapping(value = "/payment")
    public ResponseEntity<?> paymentOrderById(@RequestBody PaymentVo paymentVo) {
        OrderDto orderDtoFindById = _iOrderService.findById(paymentVo.getId());
        String link = buildUrlVnpay(orderDtoFindById.getId(), orderDtoFindById.getTotalPrice(), paymentVo.url);
//        request.getSession().setAttribute("payment_vo", paymentVo);
        return ResponseEntity.ok(link);
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
        assert dtoSave != null;
        sendMailInfoOrderBill(dtoSave, dto.getDetails(), dto.getEmail());
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

    private void sendMailInfoOrderBill(OrderDto dtoSave, List<OrderDetailDto> details, String email) {
        StringBuilder message = new StringBuilder("<h2>Thông tin hoá đơn mua hàng</h2>\n" +
                "<label>Tên khách hàng: " + dtoSave.getNameUser() + "</label><br />\n" +
                "<label>Người nhận hàng: " + dtoSave.getFullName() + "</label><br />\n" +
                "<label>Điện thoại: " + dtoSave.getPhoneNumber() + "</label><br />\n" +
                "<label>Mã giảm giá: " + dtoSave.getCodeVoucher() + "</label><br />\n" +
                "<label>Tổng tiền: " + dtoSave.getTotalPrice() + "</label>\n" +
                "<table border=\"1px\">\n" +
                "    <thead>\n" +
                "        <th>Sản phẩm</th>\n" +
                "        <th>Giá</th>\n" +
                "        <th>Giá giảm</th>\n" +
                "        <th>Số lượng</th>\n" +
                "        <th>Thành tiền</th>\n" +
                "    </thead>\n" +
                "    <tbody>\n");
        ProductDto productDto = null;
        for (OrderDetailDto x : details) {
            productDto = _iProductService.findProductById(x.getIdProduct());
            message.append("        <tr>\n" + "            <td>")
                    .append(productDto.getName())
                    .append("</td>\n")
                    .append("            <td>")
                    .append(x.getPrice())
                    .append("</td>\n")
                    .append("            <td>")
                    .append(x.getPriceSale())
                    .append("</td>\n")
                    .append("            <td>")
                    .append(x.getQuantity())
                    .append("</td>\n")
                    .append("            <td>")
                    .append(x.getPrice() * x.getQuantity() - x.getPriceSale())
                    .append("</td>\n")
                    .append("        </tr>\n");
        }
        message.append("    </tbody>\n" + "</table>");
        _iSendmailService.push(email, "Thông tin hoá đơn mua hàng", message.toString());
    }

    private String buildUrlVnpay(String idOrder, Long price, String urlSuccess) {
        String vnp_Version = "2.0.0";
        String vnp_Command = "pay";
        String orderType = "billpayment";
        String vnp_IpAddr = VnpayConfig.getIpAddress(request);
        String vnp_TmnCode = VnpayConfig.vnp_TmnCode;
        Long amount = price * 100;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        String bank_code = "";
        if (bank_code != null && bank_code.isEmpty()) {
            vnp_Params.put("vnp_BankCode", "");
        }
        vnp_Params.put("vnp_TxnRef", idOrder);
        vnp_Params.put("vnp_OrderInfo", urlSuccess);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = "vi";
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", VnpayConfig.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Date dt = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        vnp_Params.put("vnp_CreateDate", formatter.format(dt));

        //Build data to hash and querystring
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(fieldValue);
                //Build query
                try {
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VnpayConfig.Sha256(VnpayConfig.vnp_HashSecret + hashData.toString());
        queryUrl += "&vnp_SecureHashType=SHA256&vnp_SecureHash=" + vnp_SecureHash;
        return VnpayConfig.vnp_PayUrl + "?" + queryUrl;
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

    @Data
    @SessionScope
    static class PaymentVo {
        private String id;
        private String url;
    }
}
