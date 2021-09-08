package com.example.webproductspringboot.controller.web;

import com.example.webproductspringboot.service.intf.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("")
public class HomeWeb {

    @Autowired
    private IProductService _iProductService;

    @GetMapping({"", "/index.html"})
    public String home(Model model){
        model.addAttribute("sellerProduct", null);
        return "page/web/index";
    }

//    @GetMapping
//    public String listProduct(Model model, List<QuickViewProductDto> lstQuickViewProducts) {
//        model.addAttribute("lstProducts", lstQuickViewProducts);
//        return "page/web/product/list-product";
//    }

}
