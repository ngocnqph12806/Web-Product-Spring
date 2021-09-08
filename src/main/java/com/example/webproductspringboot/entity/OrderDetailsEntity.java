package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_details")
@Data
public class OrderDetailsEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private OrderEntity idOrder;
    @ManyToOne
    @JoinColumn
    private ProductEntity idProduct;
    @Column
    private Long price;
    @Column
    private Integer quantity;
    @Column
    private Long priceSale;
    @OneToMany(mappedBy = "idOrderDtails")
    private List<CustomersReturnDetailsEntity> lstCustomersReturnDetailsEntities;

}
