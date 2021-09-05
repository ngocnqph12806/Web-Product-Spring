package com.example.webproductspringboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDto {

    private String id;
    private StaffDto idStaff;
    private String details;
    private Date created;

}
