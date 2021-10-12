package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ChechoutDto {

    private String id;
    private String idUser;
    private String nameUser;
    private String idVoucher;
    private String codeVoucher;
    private Long priceVoucher;
    @NotNull
    @NotBlank
    private String paymentMethod;
    private Boolean paymentStatus;
    @NotNull
    @NotBlank
    private String fullName;
    @NotNull
    @NotBlank
    private String phoneNumber;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String village; // số nhà, thôn xóm
    @NotNull
    @NotBlank
    private String ward; // phường xã
    @NotNull
    @NotBlank
    private String district; // quận huyện
    @NotNull
    @NotBlank
    private String city; // thành phố
    private String note; // thành phố
    private String description;
    private Boolean status;
    private Date dateCreated;
    private Long totalPrice;
    private List<OrderDetailDto> details;
    private List<TransportDto> transports;


}
