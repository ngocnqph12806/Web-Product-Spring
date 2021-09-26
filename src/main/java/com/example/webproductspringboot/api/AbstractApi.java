package com.example.webproductspringboot.api;

import com.example.webproductspringboot.dto.BrandDto;
import com.example.webproductspringboot.utils.ConvertUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractApi {

    protected final Integer OK = 200;
    protected final Integer UPDATED = 200;
    protected final Integer CREATED = 201;
    protected final Integer DELETED = 204;

    protected final HttpServletRequest request;

    protected AbstractApi(HttpServletRequest request) {
        this.request = request;
    }

}
