package com.example.webproductspringboot.controller;

import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.UserDto;
import com.example.webproductspringboot.service.intf.IUserService;
import com.example.webproductspringboot.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class UserAdmin {

    @Autowired
    private IUserService _iUserService;

    @GetMapping("staff")
    public String homeStaff(Model model) {
        model.addAttribute("lstUser", _iUserService.findAllStaff());
        model.addAttribute("isStaff", true);
        return "/user/index";
    }

    @GetMapping("visit")
    public String homeVisit(Model model) {
        model.addAttribute("lstUser", _iUserService.findAllVisit());
        model.addAttribute("isStaff", false);
        return "/user/index";
    }

    @PostMapping("staff/load")
    public String loadPageStaff(@RequestParam("_p") Integer page,
                                @RequestParam(value = "_s", defaultValue = "10") Integer size,
                                Model model) {
        PageDto<List<UserDto>> pageDto = _iUserService.findStaffByPage(page, size);
        model.addAttribute("lstUser", pageDto.getContent());
        model.addAttribute("totalPage", pageDto.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("isStaff", true);
        return "/user/table-user";
    }

}
