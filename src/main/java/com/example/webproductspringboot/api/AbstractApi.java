package com.example.webproductspringboot.api;

import javax.servlet.http.HttpServletRequest;

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
