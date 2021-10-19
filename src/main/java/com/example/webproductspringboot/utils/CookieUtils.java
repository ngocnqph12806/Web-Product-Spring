package com.example.webproductspringboot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class CookieUtils {

    private static CookieUtils cookieUtils;

    public String errorsProperties(HttpServletRequest request, String path, String key) {
        ResourceBundle resourceBundle = null;
        Cookie[] cookies = request.getCookies();
        String lang = "";
        if (cookies == null) cookies = new Cookie[0];
        for (Cookie x : cookies)
            if (x.getName().equals(CookieLocaleResolver.LOCALE_REQUEST_ATTRIBUTE_NAME)) {
                lang = x.getValue().replaceAll("-", "_");
                break;
            }
        System.out.println(lang);
        if (lang.isBlank()) resourceBundle = ResourceBundle.getBundle("i18n/" + path);
        else if (lang.equals("en")) resourceBundle = ResourceBundle.getBundle("i18n/" + path + "_" + "en_US");
        else if (lang.equals("vi")) resourceBundle = ResourceBundle.getBundle("i18n/" + path + "_" + "vi_VN");
//        else resourceBundle = ResourceBundle.getBundle("i18n/" + path + "_" + lang);
        assert resourceBundle != null;
        return resourceBundle.getString(key);
    }

    public static CookieUtils get() {
        if (cookieUtils == null) cookieUtils = new CookieUtils();
        return cookieUtils;
    }

}
