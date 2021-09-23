package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "collection_brand")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public String toString() {
        return "CollectionBrandEntity{" +
                "id='" + id + '\'' +
                ", idBrand=" + (idBrand != null ? idBrand.getId() : null) +
                ", idCollection=" + (idCollection != null ? idCollection.getId() : null) +
                '}';
    }
}
