package com.example.webproductspringboot.controller;

import com.example.webproductspringboot.dto.OrderDto;
import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.dto.ReturnDto;
import com.example.webproductspringboot.service.intf.ICustomersReturnService;
import com.example.webproductspringboot.service.intf.IOrderService;
import com.example.webproductspringboot.service.intf.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class ReturnProductAdmin {

    @Autowired
    private ICustomersReturnService _iCustomersReturnService;
    @Autowired
    private IOrderService _iOrderService;
    @Autowired
    private IUserService _iUserService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("load/returns")
    public String loadReturn(@RequestParam(value = "_p", defaultValue = "0") Integer page,
                             @RequestParam(value = "_s", defaultValue = "5") Integer size,
                             Model model) {
        PageDto<List<ReturnDto>> pageDto = _iCustomersReturnService.findByPage(page, size);
        model.addAttribute("lstReturns", pageDto.getContent());
        model.addAttribute("totalPage", pageDto.getTotalPages());
        model.addAttribute("page", page);
        return "/load-returns";
    }

    @GetMapping("load/returns/form/{id}")
    public String loadFormReturn(@PathVariable("id") String id, Model model) {
        try {
            ReturnDto returnDtoFindById = _iCustomersReturnService.findById(id);
            request.getSession().setAttribute("session_dto_return", returnDtoFindById);
            model.addAttribute("return", returnDtoFindById);
            model.addAttribute("order", _iOrderService.findById(returnDtoFindById.getIdOrder()));
            model.addAttribute("lstStaff", _iUserService.findAllStaff());
            model.addAttribute("addNew", false);
            return "load-form-returns";
        } catch (Exception e) {
        }
        return "redirect:/admin/load/returns";
    }

    @GetMapping("load/returns/form/add-by-id-order/{idOrder}")
    public String loadFormReturnWithAddByIdOrder(@PathVariable("idOrder") String idOrder, Model model) {
        try {
            model.addAttribute("return", ReturnDto.builder().idOrder(idOrder).build());
            model.addAttribute("order", _iOrderService.findById(idOrder));
            model.addAttribute("lstStaff", _iUserService.findAllStaff());
            model.addAttribute("addNew", true);
            return "load-form-returns";
        } catch (Exception e) {
        }
        return "redirect:/admin/load/returns";
    }

}
