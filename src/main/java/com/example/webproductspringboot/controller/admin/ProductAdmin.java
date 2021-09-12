package com.example.webproductspringboot.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class ProductAdmin {

    @GetMapping("product")
    public String listProduct(Model model) {
//        model.addAttribute("lstStaff", _iVisitService.findAll());
        return "page/admin/product/index-product";
    }

}
