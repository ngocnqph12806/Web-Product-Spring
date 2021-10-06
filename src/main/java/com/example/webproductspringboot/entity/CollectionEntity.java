package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "collections")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class CollectionEntity {

    @Id
    @Column(length = 64)
    private String id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column
    private String description;
    @Column(nullable = false, unique = true)
    private Long idUrl;
    @Column(nullable = false, unique = true)
    private String pathUrl;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date created;
    //    @OneToMany(mappedBy = "idCollection")
//    private List<CollectionBrandEntity> lstCollectionBrandEntities;
    @OneToMany(mappedBy = "idCollection")
    private List<CollectionCategoryEntity> lstCollectionCategoryEntities;

    @Override
    public String toString() {
        return "CollectionEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
