package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "invoice_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailsEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private InvoiceEntity idInvoice;
    @ManyToOne
    @JoinColumn
    private ProductEntity idProduct;
    @Column
    private Long price;
    @Column
    private Integer quantity;

}
