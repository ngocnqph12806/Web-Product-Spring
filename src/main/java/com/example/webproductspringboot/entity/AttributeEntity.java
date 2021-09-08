package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//@Entity
@Table(name = "attribute")
@Data
public class AttributeEntity {

    @Id
    @Column(length = 64)
    private String id;
    @Column(nullable = false, length = 30)
    private String name;
    @Column
    private String description;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition="timestamp default current_timestamp")
    private Date created;
    @OneToMany(mappedBy = "idAttribute")
    private List<AttributeValueEntity> lstAttributeValueEntities;

}
