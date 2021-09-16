package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.product.FormCategoryAdminDto;
import com.example.webproductspringboot.entity.CategoryEntity;
import com.example.webproductspringboot.entity.ProductEntity;
import com.example.webproductspringboot.exception.InternalServerException;
import com.example.webproductspringboot.reponsitory.ICategoryReponsitory;
import com.example.webproductspringboot.reponsitory.IProductReponsitory;
import com.example.webproductspringboot.service.intf.IProductService;
import com.example.webproductspringboot.service.intf.IUserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class TestAPI {

    @Autowired
    private ICategoryReponsitory _iCategoryReponsitory;

    @PutMapping("/test-api/product")
    public ResponseEntity<?> getProduct(@RequestBody FormCategoryAdminDto categoryEntity) {
        try {
            System.out.println(categoryEntity);
            CategoryEntity entity  = categoryEntity.toEntity();
            entity.setIdUrl(Long.parseLong("3423"));
            entity.setStatus(true);
            entity.setCreated(new Date());
            _iCategoryReponsitory.save(entity);
        } catch (ConstraintViolationException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getConstraintName());
            System.out.println(e.getSQL());
            System.out.println(e.getCause());
//            throw new InternalServerException(e.toString());
        }
        return null;
    }


}
