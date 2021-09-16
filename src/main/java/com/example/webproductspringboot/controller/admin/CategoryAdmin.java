package com.example.webproductspringboot.controller.admin;

import com.example.webproductspringboot.service.intf.IBrandService;
import com.example.webproductspringboot.service.intf.ICategoryService;
import com.example.webproductspringboot.service.intf.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class CategoryAdmin {

    @Autowired
    private ICollectionService _iCollectionService;
    @Autowired
    private ICategoryService _iCategoryService;
    @Autowired
    private IBrandService _iBrandService;

    @GetMapping("category")
    public String homeCategory(Model model) {
        model.addAttribute("lstCategoriesAndBrand", _iCategoryService.findAllIntroCategoryAdmin());
        model.addAttribute("lstCollections", _iCollectionService.findAllIntroCollectionsAdmin());
        model.addAttribute("isCategory", true);
        return "page/admin/product/index-category-brand-collection";
    }

    @GetMapping("brand")
    public String homeBrand(Model model) {
        model.addAttribute("lstCategoriesAndBrand", _iBrandService.findAllIntroBrandAdmin());
        model.addAttribute("lstCollections", _iCollectionService.findAllIntroCollectionsAdmin());
        model.addAttribute("isCategory", false);
        return "page/admin/product/index-category-brand-collection";
    }

}
