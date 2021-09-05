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
    private AttributeValueEntity idAttributeValue;
    @Column
    private Long price;
    @Column
    private Integer quantity;
    @Column(nullable = false, length = 100)
    private String location;

}
