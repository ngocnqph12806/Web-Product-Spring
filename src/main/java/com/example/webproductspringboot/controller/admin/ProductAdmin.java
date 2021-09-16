package com.example.webproductspringboot.controller.admin;

import com.example.webproductspringboot.service.intf.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class ProductAdmin {

    @Autowired
    private IProductService _iProductService;

    @GetMapping("product")
    public String listProduct(Model model) {
        model.addAttribute("lstProduct", _iProductService.findAllIntroAdmin());
        return "page/admin/product/index-product";
    }

}
