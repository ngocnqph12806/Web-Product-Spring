package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "collection_category")
@Data
public class CollectionCategoryEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private CategoryEntity idCategory;
    @ManyToOne
    @JoinColumn
    private CollectionEntity idCollection;

}
