package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="review_product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewProductEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private ProductEntity idProduct;
    @ManyToOne
    @JoinColumn
    private UserEntity idVisit;
    @Column
    private Integer point;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, columnDefinition = "bit default 0")
    private Boolean introduce;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition="timestamp default current_timestamp")
    private Date created;
    @OneToMany(mappedBy = "idReviewProduct")
    private List<ReviewImageEntity> lstReviewImageEntities;

}
