package com.example.webproductspringboot;

import com.example.webproductspringboot.service.intf.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class WebProductSpringBootApplicationTests {

    @Autowired
    private IProductService iProductReponsitory;

    @Test
    void contextLoads() {
        iProductReponsitory.findAllIntroWeb();
    }

}
