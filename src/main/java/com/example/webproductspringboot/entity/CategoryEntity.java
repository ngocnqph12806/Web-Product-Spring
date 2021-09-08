package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "category")
@Data
public class CategoryEntity {

    @Id
    @Column(length = 64)
    private String id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false)
    private String banner;
    @Column(nullable = false, length = 100)
    private String pathUrl;
    @Column
    private String description;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date created;
    @OneToMany(mappedBy = "idCategory")
    private List<ProductEntity> lstProductEntities;
    @OneToMany(mappedBy = "idCategory")
    private List<CollectionCategoryEntity> lstCollectionCategoryEntities;

}
