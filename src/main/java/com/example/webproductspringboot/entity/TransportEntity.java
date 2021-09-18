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
    @Column(nullable = false, length = 50)
    private String fullName;
    @Column(nullable = false, length = 15)
    private String phoneNumber;
    @Column(nullable = false, length = 100)
    private String address;
    @Column
    private String description;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition="timestamp default current_timestamp")
    private Date created;

}
