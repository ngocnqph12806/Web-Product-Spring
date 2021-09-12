package com.example.webproductspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history")
@Data
public class HistoryEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private UserEntity idStaff;
    @Column(nullable = false)
    private String details;
    @Column(columnDefinition="timestamp default current_timestamp")
    private Date created;

}
