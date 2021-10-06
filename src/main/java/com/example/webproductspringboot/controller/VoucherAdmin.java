package com.example.webproductspringboot.controller;

import com.example.webproductspringboot.service.intf.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class VoucherAdmin {

    @Autowired
    private IVoucherService _iVoucherService;

    @GetMapping("voucher")
    public String listVoucher(Model model) {
        model.addAttribute("lstVoucher", _iVoucherService.findAll());
        return "/website/voucher";
    }

}
