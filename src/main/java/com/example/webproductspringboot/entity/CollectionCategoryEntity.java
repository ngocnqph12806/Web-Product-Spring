package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "collection_category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
