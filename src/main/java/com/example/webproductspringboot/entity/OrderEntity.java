package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private UserEntity idVisit;
    @ManyToOne
    @JoinColumn
    private UserEntity staffCreate;
    @ManyToOne
    @JoinColumn
    private UserEntity staffSales;
    @Column(nullable = false, length = 50)
    private String paymentMethod;
    @Column(columnDefinition = "bit default 0")
    private Boolean paymentStatus;
    @Column
    private String description;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date created;
    @OneToMany(mappedBy = "idOrder")
    private List<OrderDetailsEntity> lstOrderDetailsEntities;
    @OneToMany(mappedBy = "idOrder")
    private List<TransportEntity> lstTransportEntities;

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id='" + id + '\'' +
                ", idVisit=" + (idVisit != null ? idVisit.getId() : null) +
                ", staffCreate=" + (staffCreate != null ? staffCreate.getId() : null) +
                ", staffSales=" + (staffSales != null ? staffSales.getId() : null) +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentStatus=" + paymentStatus +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
