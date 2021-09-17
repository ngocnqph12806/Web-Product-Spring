package com.example.webproductspringboot.dto;

import com.example.webproductspringboot.vo.ReturnDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReturnDto {

    private String id;
    private String idOrder;
    private Date dateOrder;
    private String idUser;
    private String nameUser;
    private String idStaff;
    private String nameStaff;
    private String description;
    private Boolean status;
    private Date dateCreated;
    List<ReturnDetailDto> details;


}
