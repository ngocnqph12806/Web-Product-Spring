package com.example.webproductspringboot.controller;

import com.example.webproductspringboot.dto.BrandDto;
import com.example.webproductspringboot.dto.CategoryDto;
import com.example.webproductspringboot.dto.PageDto;
import com.example.webproductspringboot.service.intf.IBrandService;
import com.example.webproductspringboot.service.intf.ICategoryService;
import com.example.webproductspringboot.service.intf.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class CategoryAdmin {

    @Autowired
    private ICollectionService _iCollectionService;
    @Autowired
    private ICategoryService _iCategoryService;
    @Autowired
    private IBrandService _iBrandService;

    @GetMapping("load/category")
    public String loadCategory(@RequestParam(value = "_p", defaultValue = "0") Integer page,
                               @RequestParam(value = "_s", defaultValue = "5") Integer size,
                               Model model) {
        PageDto<List<CategoryDto>> pageDto = _iCategoryService.findByPage(page, size);
        model.addAttribute("lstCategoriesAndBrand", pageDto.getContent());
        model.addAttribute("totalPage", pageDto.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("lstCollections", _iCollectionService.findAll());
        model.addAttribute("isCategory", true);
        return "/load-not-product";
    }

    @GetMapping("load/brand")
    public String loadBrand(@RequestParam(value = "_p", defaultValue = "0") Integer page,
                            @RequestParam(value = "_s", defaultValue = "5") Integer size,
                            Model model) {
        PageDto<List<BrandDto>> pageDto = _iBrandService.findByPage(page, size);
        model.addAttribute("lstCategoriesAndBrand", pageDto.getContent());
        model.addAttribute("totalPage", pageDto.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("lstCollections", _iCollectionService.findAll());
        model.addAttribute("isCategory", false);
        return "/load-not-product";
    }

}
