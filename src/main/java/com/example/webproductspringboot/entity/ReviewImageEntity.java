package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "review_image")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewImageEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private ReviewProductEntity idReviewProduct;
    @Column(nullable = false)
    private String pathImage;

    @Override
    public String toString() {
        return "ReviewImageEntity{" +
                "id='" + id + '\'' +
                ", idReviewProduct=" + (idReviewProduct != null ? idReviewProduct.getId() : null) +
                ", pathImage='" + pathImage + '\'' +
                '}';
    }
}
