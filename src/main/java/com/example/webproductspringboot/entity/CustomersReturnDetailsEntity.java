package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "customer_return_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomersReturnDetailsEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private CustomersReturnEntity idCustomersReturn;
    @ManyToOne
    @JoinColumn
    private OrderDetailsEntity idOrderDtails;
    @Column
    private Integer quantity;

    @Override
    public String toString() {
        return "CustomersReturnDetailsEntity{" +
                "id='" + id + '\'' +
                ", idCustomersReturn=" + (idCustomersReturn != null ? idCustomersReturn.getId() : null) +
                ", idOrderDtails=" + (idOrderDtails != null ? idOrderDtails.getId() : null) +
                ", quantity=" + quantity +
                '}';
    }
}
