package com.example.webproductspringboot.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeWeb {

    @GetMapping
    public String home(){
        return "/component/web/index";
    }

}
