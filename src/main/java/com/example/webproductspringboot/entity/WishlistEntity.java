package com.example.webproductspringboot.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "wishlist")
@Builder
@Data
public class WishlistEntity {

    @Id
    @Column(nullable = false, length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private ProductEntity idProduct;
    @ManyToOne
    @JoinColumn
    private UserEntity idVisit;

}
