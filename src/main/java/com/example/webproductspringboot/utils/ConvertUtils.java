package com.example.webproductspringboot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertUtils {

    public static ConvertUtils convertUtils;

    public String dateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static ConvertUtils get() {
        if (convertUtils == null) {
            convertUtils = new ConvertUtils();
        }
        return convertUtils;
    }

}
