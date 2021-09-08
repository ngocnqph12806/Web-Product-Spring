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
    @Column(nullable = false)
    private String location;
    @Column
    private String description;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition="timestamp default current_timestamp")
    private Date created;
    @OneToMany(mappedBy = "idProduct")
    private List<ProductImageEntity> lstProductImageEntities;
    @OneToMany(mappedBy = "idProduct")
    private List<OrderDetailsEntity> lstOrderDetailsEntities;
    @OneToMany(mappedBy = "idProduct")
    private List<InvoiceDetailsEntity> lstInvoiceDetailsEntities;

}
