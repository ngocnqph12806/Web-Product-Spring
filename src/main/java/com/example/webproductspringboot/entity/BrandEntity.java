package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "brand")
@Data
public class BrandEntity {

    @Id
    @Column(length = 64)
    private String id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 15)
    private String phoneNumber;
    @Column(nullable = false, length = 100)
    private String address;
    @Column(nullable = false, length = 50)
    private String email;
    @Column
    private String description;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition="timestamp default current_timestamp")
    private Date created;
    @OneToMany(mappedBy = "idBrand")
    private List<ProductEntity> lstProduct;
    @OneToMany(mappedBy = "idBrand")
    private List<CollectionBrandEntity> lstCollectionBrandEntities;

}
