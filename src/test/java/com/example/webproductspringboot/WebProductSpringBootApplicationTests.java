package com.example.webproductspringboot;

import com.example.webproductspringboot.dto.product.IntroProductDto;
import com.example.webproductspringboot.entity.ProductEntity;
import com.example.webproductspringboot.reponsitory.IProductReponsitory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class WebProductSpringBootApplicationTests {

    @Autowired
    private IProductReponsitory iProductReponsitory;

    @Test
    void contextLoads() {
    }

}
