package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "brand")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity {

    @Id
    @Column(length = 64)
    private String id;
    @Column(nullable = false, length = 100, unique = true)
    private String name;
    @Column(nullable = false, length = 15, unique = true)
    private String phoneNumber;
    @Column(nullable = false, length = 100)
    private String address;
    @Column(nullable = false, length = 50, unique = true)
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
