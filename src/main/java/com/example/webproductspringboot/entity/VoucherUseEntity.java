package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "voucher_used")
@Data
public class VoucherUseEntity {

    @Id
    @Column(nullable = false, length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private VoucherEntity idVoucher;
    @ManyToOne
    @JoinColumn
    private UserEntity idVisit;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition="timestamp default current_timestamp")
    private Date created;

}
