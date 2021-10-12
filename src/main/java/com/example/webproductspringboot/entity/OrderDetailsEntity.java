package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private OrderEntity idOrder;
    @ManyToOne
    @JoinColumn
    private ProductEntity idProduct;
    @Column(nullable = false)
    private Long price;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false, columnDefinition = "int default 0")
    private Long priceSale;
    @OneToMany(mappedBy = "idOrderDtails")
    private List<CustomersReturnDetailsEntity> lstCustomersReturnDetailsEntities;

    @Override
    public String toString() {
        return "OrderDetailsEntity{" +
                "id='" + id + '\'' +
                ", idOrder=" + (idOrder != null ? idOrder.getId() : null) +
                ", idProduct=" + (idProduct != null ? idProduct.getId() : null) +
                ", price=" + price +
                ", quantity=" + quantity +
                ", priceSale=" + priceSale +
                '}';
    }
}
