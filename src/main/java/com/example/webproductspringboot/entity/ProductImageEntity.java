package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_image")
@Data
public class ProductImageEntity {

    @Id
    @Column(length = 64)
    private String id;
    @Column
    private String path;
    @ManyToOne
    @JoinColumn
    private ProductEntity idProduct;

}
