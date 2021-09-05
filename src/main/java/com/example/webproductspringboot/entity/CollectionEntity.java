package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "collections")
@Data
public class CollectionEntity {

    @Id
    @Column(length = 64)
    private String id;
    @Column(nullable = false,length = 50)
    private String name;
    @Column
    private String description;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition="timestamp default current_timestamp")
    private Date created;

}
