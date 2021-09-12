package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "review_image")
@Data
public class ReviewImageEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private ReviewProductEntity idReviewProduct;
    @Column(nullable = false)
    private String pathImage;

}
