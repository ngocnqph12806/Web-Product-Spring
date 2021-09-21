package com.example.webproductspringboot.controller.admin;

import com.example.webproductspringboot.dto.ProductDto;
import com.example.webproductspringboot.service.intf.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/")
public class ProductAdmin {

    @Autowired
    private IProductService _iProductService;

    @GetMapping("product")
    public String listProduct(Model model) {
        model.addAttribute("lstProduct", _iProductService.findAll());
        return "page/admin/product/index-product";
    }

    @GetMapping(value = "product/edit", params = "id")
    public String editWithId(@RequestParam("id") String id, RedirectAttributes ra) {
        try {
            ra.addFlashAttribute("product", _iProductService.findById(id));
        } catch (Exception e) {
            ra.addFlashAttribute("product", new ProductDto());
        }
        return "redirect:/admin/product/edit";
    }

    @GetMapping(value = {"product/edit", "product/add"})
    public String formProduct() {
        return "page/admin/product/form-product";
    }

}
