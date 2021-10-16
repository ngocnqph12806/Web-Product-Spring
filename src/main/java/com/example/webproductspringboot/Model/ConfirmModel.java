package com.example.webproductspringboot.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ConfirmModel<T> {

    private Integer code;
    private T model;
    private Date time;
    private String type;

    @Override
    public String toString() {
        return "ConfirmModel{" +
                "code=" + code +
                ", time=" + time +
                '}';
    }
}
