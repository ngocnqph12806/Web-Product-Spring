package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String id;
    @NotNull(message = "order.not.null.id.user")
    @NotBlank(message = "order.not.blank.id.user")
    private String idUser;
    private String nameUser;
    private String idVoucher;
    private String codeVoucher;
    private Long priceVoucher;
    private String idCreator;
    private String nameCreator;
    @NotNull(message = "order.not.null.id.saller")
    @NotBlank(message = "order.not.blank.id.saller")
    private String idSaller;
    private String nameSaller;
    @NotNull(message = "order.not.null.payment.method")
    @NotBlank(message = "order.not.blank.payment.method")
//    @Pattern(regexp = "[A-Za-z]+",
//            message = "order.pattern.payment.method")
    private String paymentMethod;
    private Boolean paymentStatus;
    @NotNull(message = "order.not.null.full.name")
    @NotBlank(message = "order.not.blank.full.name")
//    @Pattern(regexp = "[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]{5,32}$",
//    message = "order.pattern.full.name")
    private String fullName;
    @NotNull(message = "order.not.null.phone.number")
    @NotBlank(message = "order.not.blank.phone.number")
//    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}",
//    message = "order.pattern.phone.number")
    private String phoneNumber;
    @NotNull(message = "order.not.null.email")
    @NotBlank(message = "order.not.blank.email")
//    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$",
//            message = "order.pattern.email")
    private String email;
    @NotNull(message = "order.not.null.village")
    @NotBlank(message = "order.not.blank.village")
    private String village; // số nhà, thôn xóm
    @NotNull(message = "order.not.null.ward")
    @NotBlank(message = "order.not.blank.ward")
    private String ward; // phường xã
    @NotNull(message = "order.not.null.district")
    @NotBlank(message = "order.not.blank.district")
    private String district; // quận huyện
    @NotNull(message = "order.not.null.city")
    @NotBlank(message = "order.not.blank.city")
    private String city; // thành phố
    private String note; // thành phố
    private String description;
    private Boolean status;
    private Date dateCreated;
    private Long totalPrice;
    private List<OrderDetailDto> details;
    private List<TransportDto> transports;


}
