package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
@Data
public class ProductEntity {

    @Id
    @Column(length = 64)
    private String id;
    @Column(nullable = false, length = 100)
    private String name;
    @ManyToOne
    @JoinColumn
    private BrandEntity idBrand;
    @ManyToOne
    @JoinColumn
    private CategoryEntity idCategory;
    @Column
    private Long priceSale;
    @Column
    private String description;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition="timestamp default current_timestamp")
    private Date created;

}
