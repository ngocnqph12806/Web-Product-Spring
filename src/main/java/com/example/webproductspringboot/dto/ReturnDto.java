package com.example.webproductspringboot.dto;

import com.example.webproductspringboot.vo.ReturnDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Scope("session")
public class ReturnDto {

    private String id;
    @NotNull(message = "return.not.null.id.order")
    @NotBlank(message = "return.not.blank.id.order")
    private String idOrder;
    private Date dateOrder;
    private String nameUser;
//    @NotNull
//    @NotBlank
    private String idStaff;
    private String nameStaff;
    @NotNull(message = "return.not.null.description")
    @NotBlank(message = "return.not.blank.description")
    private String description;
    private Boolean status;
    private Date dateCreated;
    private Long totalMoney;
    List<ReturnDetailDto> details;


}
