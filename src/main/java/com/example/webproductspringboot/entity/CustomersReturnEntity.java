package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer_return")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomersReturnEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private OrderEntity idOrder;
    @ManyToOne
    @JoinColumn
    private UserEntity idStaff;
    @Column
    private String description;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date created;
    @OneToMany(mappedBy = "idCustomersReturn")
    private List<CustomersReturnDetailsEntity> lstCustomersReturnDetailsEntities;

    @Override
    public String toString() {
        return "CustomersReturnEntity{" +
                "id='" + id + '\'' +
                ", idOrder=" + (idOrder != null ? idOrder.getId() : null) +
                ", idStaff=" + (idStaff != null ? idStaff.getId() : null) +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
