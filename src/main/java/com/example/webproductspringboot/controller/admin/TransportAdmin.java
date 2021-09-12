package com.example.webproductspringboot.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class TransportAdmin {

    @GetMapping("transport")
    public String listCollections(Model model) {
//        model.addAttribute("lstStaff", _iVisitService.findAll());
        return "page/admin/order/transport";
    }

}
