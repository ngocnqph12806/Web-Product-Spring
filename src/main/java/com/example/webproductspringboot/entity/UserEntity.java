package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column(nullable = false, length = 64)
    private String id;
    @Column(nullable = false, length = 50)
    private String fullName;
    @Column(nullable = false)
    private Date dateOfBirth;
    @Column(nullable = false, length = 15, unique = true)
    private String phoneNumber;
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false, length = 32, unique = true)
    private String username;
    @Column(length = 64)
    private String password;
    @Column(nullable = false, length = 100)
    private String address;
    @Column(nullable = false)
    private String avatar;
    @Column(nullable = false, length = 10)
    private String role;
    @Column(nullable = false, columnDefinition = "bit default 0")
    private Boolean status;
    @Column(nullable = false, columnDefinition = "bit default 0")
    private Boolean block;
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
    private List<CustomersReturnEntity> lstCustomersReturnEntitiesWithStaff;

    //VISIT
    @OneToMany(mappedBy = "idVisit")
    private List<ReviewProductEntity> lstReviewProductEntities;
    @OneToMany(mappedBy = "idVisit")
    private List<VoucherUseEntity> lstVoucherUseEntityList;
    @OneToMany(mappedBy = "idVisit")
    private List<OrderEntity> lstOrderEntities;
    @OneToMany(mappedBy = "idVisit")
    private List<CustomersReturnEntity> lstCustomersReturnEntitiesWithVisit;
    @OneToMany(mappedBy = "idVisitOrder")
    private List<TransportEntity> lstTransportEntities;
}
