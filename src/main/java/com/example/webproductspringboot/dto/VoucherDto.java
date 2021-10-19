package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDto {

    private String id;
    @NotNull(message = "NotNull.voucher.code")
    @NotBlank(message = "NotBlank.voucher.code")
//    @Pattern(regexp = "^[a-zA-Z0-9\\-]+$", message = "Pattern.voucher.code")
    @Size(min = 5, max = 30, message = "Size.voucher.code")
    private String code;
    @NotNull(message = "NotNull.voucher.title")
    @NotBlank(message = "NotBlank.voucher.title")
//    @Pattern(regexp = "^[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ \\-.]+$",
//            message = "Pattern.voucher.title")
    @Size(min = 5, max = 100, message = "Size.voucher.title")
    private String title;
    private String idStaff;
    private String nameStaff;
    @NotNull(message = "NotNull.voucher.quantity")
    @Min(value = 1, message = "Min.voucher.quantity")
    private Integer quantity;
    @NotNull(message = "NotNull.voucher.priceSale")
    @Min(value = 1, message = "Min.voucher.priceSale")
    private Long priceSale;
    @NotNull(message = "NotNull.voucher.dateStart")
    private Date dateStart;
    @NotNull(message = "NotNull.voucher.dateEnd")
    private Date dateEnd;
    @NotNull(message = "NotNull.voucher.description")
    @NotBlank(message = "NotBlank.voucher.description")
    @Size(min = 100, message = "Size.voucher.description")
    private String description;
    private Boolean status;
    private Date dateCreated;
    private Integer quantityUsed;

}
