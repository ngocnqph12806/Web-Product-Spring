package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transport")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransportEntity {

    @Id
    @Column(nullable = false, length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private OrderEntity idOrder;
    @Column
    private String description;
    @Column(nullable = false)
    private String statusTransport;
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date created;

    @Override
    public String toString() {
        return "TransportEntity{" +
                "id='" + id + '\'' +
                ", idOrder=" + (idOrder != null ? idOrder.getId() : null) +
                ", description='" + description + '\'' +
                ", status=" + statusTransport +
                '}';
    }
}
