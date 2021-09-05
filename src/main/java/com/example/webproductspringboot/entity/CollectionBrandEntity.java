package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "collection_brand")
@Data
public class CollectionBrandEntity {
    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private BrandEntity idBrand;
    @ManyToOne
    @JoinColumn
    private CollectionEntity idCollection;

}
