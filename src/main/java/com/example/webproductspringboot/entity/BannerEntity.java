package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="banner")
@Data
public class BannerEntity {

    @Id
    @Column(length = 64)
    private String id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false)
    private String pathImage;
    @Column(nullable = false)
    private String pathLink;
    @Column
    private String description;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition="timestamp default current_timestamp")
    private Date created;

}
