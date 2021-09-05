package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "attribute_value")
@Data
public class AttributeValueEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private AttributeEntity idAttribute;
    @Column(nullable = false, length = 10)
    private String value;

}
