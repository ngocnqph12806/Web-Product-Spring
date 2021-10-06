package com.example.webproductspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class OrderAdmin {

    @PostMapping("bill-order/load")
    public String listCollections(Model model) {
//        model.addAttribute("lstStaff", _iVisitService.findAll());
        return "/order/index";
    }

}
