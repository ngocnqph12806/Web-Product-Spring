package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product_image")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageEntity {

    @Id
    @Column(length = 64)
    private String id;
    @Column
    private String path;
    @ManyToOne
    @JoinColumn
    private ProductEntity idProduct;

    @Override
    public String toString() {
        return "ProductImageEntity{" +
                "id='" + id + '\'' +
                ", path='" + path + '\'' +
                ", idProduct=" + (idProduct != null ? idProduct.getId() : null) +
                '}';
    }
}
