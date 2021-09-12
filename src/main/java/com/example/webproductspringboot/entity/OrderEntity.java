package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private UserEntity idVisit;
    @ManyToOne
    @JoinColumn
    private UserEntity staffCreate;
    @ManyToOne
    @JoinColumn
    private UserEntity staffSales;
    @Column(nullable = false, length = 50)
    private String paymentMethod;
    @Column(columnDefinition = "bit default 0")
    private Boolean paymentStatus;
    @Column
    private String description;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition="timestamp default current_timestamp")
    private Date created;
    @OneToMany(mappedBy = "idOrder")
    private List<OrderDetailsEntity> lstOrderDetailsEntities;
    @OneToMany(mappedBy = "idOrder")
    private List<TransportEntity> lstTransportEntities;

}
