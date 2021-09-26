package com.example.webproductspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "voucher")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoucherEntity {

    @Id
    @Column(nullable = false, length = 64)
    private String id;
    @Column(nullable = false, length = 30)
    private String code;
    @Column(nullable = false, length = 100)
    private String title;
    @ManyToOne
    @JoinColumn
    private UserEntity idStaff;
    @Column
    private Integer quantity;
    @Column
    private Long priceSale;
    @Column
    private Date dateStart;
    @Column
    private Date dateEnd;
    @Column(nullable = false, columnDefinition = "text")
    private String description;
    @Column(nullable = false, columnDefinition = "bit default 1")
    private Boolean status;
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date created;
    @OneToMany(mappedBy = "idVoucher")
    private List<VoucherUseEntity> lstVoucherUseEntities;

    @Override
    public String toString() {
        return "VoucherEntity{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", idStaff=" + (idStaff != null ? idStaff.getId() : null) +
                ", quantity=" + quantity +
                ", priceSale=" + priceSale +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
