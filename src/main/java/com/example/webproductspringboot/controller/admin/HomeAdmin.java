package com.example.webproductspringboot.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class HomeAdmin {

    @GetMapping({"", "/index.html"})
    public String home() {
        return "page/admin/index";
    }

}
