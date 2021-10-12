package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "wishlist")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
