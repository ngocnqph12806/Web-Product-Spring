package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "value_details")
@Data
public class ValueDetailsEntity {

    @Id
    @Column(length = 64)
    private String id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "idValueDetails")
    private List<ProductDetailsEntity> lstProductDetailsEntities;

}
