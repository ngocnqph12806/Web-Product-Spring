package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoice")
@Data
public class InvoiceEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private UserEntity idStaffCreate;
    @ManyToOne
    @JoinColumn
    private UserEntity idStaffCheck;
    @Column
    private String description;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition="timestamp default current_timestamp")
    private Date created;
    @OneToMany(mappedBy = "idInvoice")
    private List<InvoiceDetailsEntity> lstInvoiceDetailsEntities;

}
