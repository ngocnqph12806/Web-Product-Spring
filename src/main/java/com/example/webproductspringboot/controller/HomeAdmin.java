package com.example.webproductspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class HomeAdmin {

    @GetMapping({"", "/index.html"})
    public String home() {
        return "/index";
    }

    @PostMapping({"load-page"})
    public String loadPage() {
        return "load-page";
    }

}
