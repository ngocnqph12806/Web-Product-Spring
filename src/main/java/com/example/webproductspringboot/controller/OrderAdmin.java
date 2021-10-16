package com.example.webproductspringboot.controller;

import com.example.webproductspringboot.dto.InvoiceDto;
import com.example.webproductspringboot.dto.OrderDto;
import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.service.intf.IOrderService;
import com.example.webproductspringboot.service.intf.IProductService;
import com.example.webproductspringboot.service.intf.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class OrderAdmin {

    @Autowired
    private IOrderService _iOrderService;
    @Autowired
    private IUserService _iUserService;
    @Autowired
    private IProductService _iProductService;

    @GetMapping("load/bill-order")
    public String listCollections(@RequestParam(value = "_p", defaultValue = "0") Integer page,
                                  @RequestParam(value = "_s", defaultValue = "5") Integer size,
                                  Model model) {
        PageDto<List<OrderDto>> pageDto = _iOrderService.findByPage(page, size);
        model.addAttribute("lstOrder", pageDto.getContent());
        model.addAttribute("totalPage", pageDto.getTotalPages());
        model.addAttribute("page", page);
        return "/load-order";
    }

    @GetMapping("load/bill-order/form")
    public String loadFormInvoice(Model model) {
        model.addAttribute("order", new OrderDto());
        model.addAttribute("lstVisit", _iUserService.findAll());
        model.addAttribute("lstStaff", _iUserService.findAllStaff());
        model.addAttribute("lstProduct", _iProductService.findAllProduct());
        return "load-form-order";
    }

    @GetMapping("load/bill-order/form/{id}")
    public String loadFormEditInvoice(@PathVariable("id") String id, Model model) {
        try {
            model.addAttribute("order", _iOrderService.findById(id));
            model.addAttribute("lstVisit", _iUserService.findAll());
            model.addAttribute("lstStaff", _iUserService.findAllStaff());
            model.addAttribute("lstProduct", _iProductService.findAllProduct());
            return "load-form-order";
        } catch (Exception e) {
        }
        return "/load-order";
    }

    @GetMapping("load/bill-order/form/product")
    public String loadInputProductFormInvoice(Model model) {
        model.addAttribute("order", new InvoiceDto());
        model.addAttribute("lstProduct", _iProductService.findAllProduct());
        return "load-input-product-form-order";
    }

}
