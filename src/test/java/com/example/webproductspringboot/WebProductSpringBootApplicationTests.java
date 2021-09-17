package com.example.webproductspringboot;

import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.reponsitory.IUserReponsitory;
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
    private IUserReponsitory _iUserReponsitory;

    @Test
    void contextLoads() {
        UserEntity entity = new UserEntity();
        entity.setBlock(false);
        entity.setId("b43f");
        entity.setFullName("Quang ");
        entity.setUsername("username");
        entity.setEmail("ngoc2");
        entity.setPhoneNumber("0345");
//        System.out.println(_iUserReponsitory.sortAll(entity.getId(), entity.getFullName(), entity.getUsername(), entity.getEmail(), entity.getPhoneNumber()).size());
    }

}
