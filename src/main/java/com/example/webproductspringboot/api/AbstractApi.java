package com.example.webproductspringboot.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractApi {

    protected final Integer OK = 200;
    protected final Integer UPDATED = 200;
    protected final Integer CREATED = 201;
    protected final Integer DELETED = 204;

    protected final HttpServletRequest request;
    protected final HttpServletResponse response;

    protected AbstractApi(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

}
