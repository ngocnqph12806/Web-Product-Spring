package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryEntity {

    @Id
    @Column(length = 64)
    private String id;
    @ManyToOne
    @JoinColumn
    private UserEntity idStaff;
    @Column(nullable = false, columnDefinition = "text")
    private String details;
    @Column(nullable = false)
    private String description;
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date created;

    @Override
    public String toString() {
        return "HistoryEntity{" +
                "id='" + id + '\'' +
                ", idStaff=" + idStaff +
                ", details='" + details + '\'' +
                '}';
    }
}
