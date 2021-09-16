package com.example.webproductspringboot.controller.admin;

import com.example.webproductspringboot.service.intf.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/")
public class UserAdmin {

    @Autowired
    private IUserService _iUserService;

    @GetMapping("staff")
    public String homeStaff(Model model) {
        model.addAttribute("lstUser", _iUserService.findAllIntroStaff());
        model.addAttribute("isStaff", true);
        return "page/admin/user/index";
    }

    @GetMapping("visit")
    public String homeVisit(Model model) {
        model.addAttribute("lstUser", _iUserService.findAllIntroVisit());
        model.addAttribute("isStaff", false);
        return "page/admin/user/index";
    }

}
