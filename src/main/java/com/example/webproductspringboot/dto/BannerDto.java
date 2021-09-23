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
public class BannerDto {

    private String id;
    @NotNull(message = "Vui lòng nhập tiêu đề banner")
    @NotEmpty(message = "Tiêu đề không được để trống banner")
    @Size(min = 10, max = 150,
            message = "Tiêu đề banner từ 10 đến 150 ký tự")
    private String title;
    @NotNull(message = "Vui lòng chọn hình ảnh banner")
    @NotEmpty(message = "Banner hình ảnh không được để trống")
    @Pattern(regexp = ".*\\.(gif|jpe?g|bmp|png)",
            message = "Hình ảnh không đúng định dạng .gif .jpg .bmp .png")
    @Size(min = 10, max = 255,
            message = "Tên hình ảnh banner từ 10 đến 255 ký tự")
    private String pathImage;
    @NotNull(message = "Vui lòng nhập đường Đường dẫn banner trỏ tới")
    @NotEmpty(message = "Đường dẫn banner trỏ tới không được để trống")
    @Pattern(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]+",
            message = "Đường dẫn banner trỏ tới không đúng")
    @Size(min = 10, max = 255,
            message = "Đường dẫn banner trỏ tới chỉ từ 10 tới 255 ký tự")
    private String link;
    @NotNull(message = "Vui lòng nhập mô tả banner")
    @NotEmpty(message = "Mô tả không được để trống")
    @Size(min = 100, message = "Mô tả phải từ 10 ký tự.")
    private String description;
    private Boolean status;
    private Date dateCreated;

}
