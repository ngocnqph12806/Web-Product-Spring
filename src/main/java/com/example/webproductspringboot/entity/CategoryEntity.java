package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @Column(length = 64)
    private String id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false)
    private String banner;
    @Column(nullable = false)
    private Long idUrl;
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

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", banner='" + banner + '\'' +
                ", idUrl=" + idUrl +
                ", pathUrl='" + pathUrl + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
