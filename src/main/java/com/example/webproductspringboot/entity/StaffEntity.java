package com.example.webproductspringboot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "staff")
@Data
public class StaffEntity {

    @Id
    @Column(nullable = false, length = 64)
    private String id;
    @Column(nullable = false, length = 50)
    private String fullName;
    @Column(nullable = false)
    private Date dateOfBirth;
    @Column(nullable = false, length = 15)
    private String phoneNumber;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 32)
    private String username;
    @Column(nullable = false, length = 64)
    private String password;
    @Column(nullable = false, length = 100)
    private String address;
    @Column(nullable = false)
    private String avatar;
    @Column(nullable = false, length = 10)
    private String role;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition="timestamp default current_timestamp")
    private Date created;

//    @OneToMany(mappedBy = "idStaff")
//    private List<VoucherUseEntity> lstVoucherUseEntities;
    @OneToMany(mappedBy = "idStaffCreate")
    private List<InvoiceEntity> lstInvoiceEntitiesCreate;
    @OneToMany(mappedBy = "idStaffCheck")
    private List<InvoiceEntity> lstInvoiceEntitiesCheck;
    @OneToMany(mappedBy = "staffCreate")
    private List<OrderEntity> lstOrderEntitiesCreate;
    @OneToMany(mappedBy = "staffSales")
    private List<OrderEntity> lstOrderEntitieSale;
    @OneToMany(mappedBy = "idStaff")
    private List<HistoryEntity> lstHistoryEntities;
    @OneToMany(mappedBy = "idStaff")
    private List<CustomersReturnEntity> lstCustomersReturnEntities;

}
