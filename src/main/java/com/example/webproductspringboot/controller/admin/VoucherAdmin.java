package com.example.webproductspringboot.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class VoucherAdmin {

    @GetMapping("voucher")
    public String listVoucher(Model model) {
//        model.addAttribute("lstStaff", _iVisitService.findAll());
        return "page/admin/website/voucher";
    }

}
