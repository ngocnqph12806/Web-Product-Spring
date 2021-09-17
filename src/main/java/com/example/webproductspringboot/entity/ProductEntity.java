package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    private Long price;
    @Column
    private Long priceSale;
    @Column
    private Integer quantity;
    @Column(nullable = false, length = 20)
    private String color;
    @Column(nullable = false, length = 100)
    private String categoryDetails;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private Long idUrl;
    @Column(nullable = false)
    private String pathUrl;
    @Column(nullable = false)
    private String pathUserManual;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition="timestamp default current_timestamp")
    private Date created;
    @OneToMany(mappedBy = "idProduct")
    private List<ProductDetailsEntity> lstProductDetailsEntities;
    @OneToMany(mappedBy = "idProduct")
    private List<ProductImageEntity> lstProductImageEntities;
//    @OneToMany(mappedBy = "idProduct")
//    private List<OrderDetailsEntity> lstOrderDetailsEntities;
//    @OneToMany(mappedBy = "idProduct")
//    private List<InvoiceDetailsEntity> lstInvoiceDetailsEntities;
    @OneToMany(mappedBy = "idProduct")
    private List<ReviewProductEntity> lstReviewProductEntities;
}
