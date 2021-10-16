package com.example.webproductspringboot.controller;

import com.example.webproductspringboot.dto.InvoiceDto;
import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.service.intf.IInvoiceService;
import com.example.webproductspringboot.service.intf.IProductService;
import com.example.webproductspringboot.service.intf.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class InvoiceAdmin {

    @Autowired
    private IInvoiceService _iInvoiceService;
    @Autowired
    private IUserService _iUserService;
    @Autowired
    private IProductService _iProductService;

    @GetMapping("load/invoice")
    public String loadInvoice(@RequestParam(value = "_p", defaultValue = "0") Integer page,
                              @RequestParam(value = "_s", defaultValue = "5") Integer size,
                              Model model) {
        PageDto<List<InvoiceDto>> pageDto = _iInvoiceService.findByPage(page, size);
        model.addAttribute("lstInvoice", pageDto.getContent());
        model.addAttribute("totalPage", pageDto.getTotalPages());
        model.addAttribute("page", page);
        return "/load-invoice";
    }

    @GetMapping("load/invoice/form")
    public String loadFormInvoice(Model model) {
        model.addAttribute("invoice", new InvoiceDto());
        model.addAttribute("lstUser", _iUserService.findAllStaff());
        model.addAttribute("lstProduct", _iProductService.findAllProduct());
        return "load-form-invoice";
    }

    @GetMapping("load/invoice/form/{id")
    public String loadFormEditInvoice(@PathVariable("id") String id, Model model) {
        try {
            model.addAttribute("invoice", _iInvoiceService.findById(id));
            model.addAttribute("lstUser", _iUserService.findAllStaff());
            model.addAttribute("lstProduct", _iProductService.findAllProduct());
            return "load-form-invoice";
        } catch (Exception e) {
        }
        return "/load-invoice";
    }

    @GetMapping("load/invoice/form/product")
    public String loadInputProductFormInvoice(Model model) {
        model.addAttribute("invoice", new InvoiceDto());
        model.addAttribute("lstProduct", _iProductService.findAllProduct());
        return "load-input-product-form-invoice";
    }

}
