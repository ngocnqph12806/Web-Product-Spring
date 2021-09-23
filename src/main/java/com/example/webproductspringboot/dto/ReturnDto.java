package com.example.webproductspringboot.dto;

import com.example.webproductspringboot.vo.ReturnDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReturnDto {

    private String id;
    @NotNull
    @NotEmpty
    private String idOrder;
    private Date dateOrder;
    @NotNull
    @NotEmpty
    private String idUser;
    private String nameUser;
    @NotNull
    @NotEmpty
    private String idStaff;
    private String nameStaff;
    @NotNull
    @NotEmpty
    private String description;
    private Boolean status;
    private Date dateCreated;
    List<ReturnDetailDto> details;


}
