package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_details")
@Data
public class ProductDetailsEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private ProductEntity idProduct;
    @ManyToOne
    @JoinColumn
    private ValueDetailsEntity idValueDetails;
    @Column(nullable = false, length = 150)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, length = 150)
    private String pathBanner;


}
