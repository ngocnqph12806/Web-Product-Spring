package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@Data
public class OrderDtailsEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private OrderEntity idOrder;
    @ManyToOne
    @JoinColumn
    private ProductDetailsEntity idProductDetails;
    @Column
    private Long price;
    @Column
    private Integer quantity;
    @Column
    private Long priceSale;

}
