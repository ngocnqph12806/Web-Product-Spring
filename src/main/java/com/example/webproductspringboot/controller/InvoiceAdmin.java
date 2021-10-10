package com.example.webproductspringboot.controller;

import com.example.webproductspringboot.dto.InvoiceDto;
import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.service.intf.IInvoiceService;
import com.example.webproductspringboot.service.intf.IProductService;
import com.example.webproductspringboot.service.intf.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("invoice/load")
    public String loadInvoice(@RequestParam("_p") Integer page,
                              @RequestParam(value = "_s", defaultValue = "10") Integer size,
                              Model model) {
        PageDto<List<InvoiceDto>> pageDto = _iInvoiceService.findByPage(page, size);
        model.addAttribute("lstInvoice", pageDto.getContent());
        model.addAttribute("totalPage", pageDto.getTotalPages());
        model.addAttribute("page", page);
        return "/load-invoice";
    }

    @PostMapping("invoice/form/load")
    public String loadFormInvoice(Model model) {
        model.addAttribute("invoice", new InvoiceDto());
        model.addAttribute("lstUser", _iUserService.findAllStaff());
        model.addAttribute("lstProduct", _iProductService.findAllProduct());
        return "load-form-invoice";
    }

    @PostMapping("invoice/form/{id}/load")
    public String loadFormEditInvoice(@PathVariable("id") String id, Model model) {
        try {
            model.addAttribute("invoice", _iInvoiceService.findById(id));
            model.addAttribute("lstUser", _iUserService.findAllStaff());
            model.addAttribute("lstProduct", _iProductService.findAllProduct());
            return "load-form-invoice";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/load-invoice";
    }

    @PostMapping("invoice/form/product/load")
    public String loadInputProductFormInvoice(Model model) {
        model.addAttribute("invoice", new InvoiceDto());
        model.addAttribute("lstProduct", _iProductService.findAllProduct());
        return "load-input-product-form-invoice";
    }

}
