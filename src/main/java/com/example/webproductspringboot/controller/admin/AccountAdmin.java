package com.example.webproductspringboot.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountAdmin {

    @GetMapping("/admin/signin")
    public String signinAdmin() {
        return "/page/admin/account/signin";
    }

    @GetMapping("/admin/signup")
    public String signupAdmin() {
        return "/page/admin/account/signup";
    }

}
