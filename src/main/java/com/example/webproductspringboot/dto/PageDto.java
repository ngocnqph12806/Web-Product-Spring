package com.example.webproductspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PageDto<T> {

    private Integer totalPages;
    private Integer totalItems;
    private T content;

    public PageDto(Integer totalPages, Integer totalItems, T content) {
        this.totalPages = totalPages;
        this.totalItems = totalItems;
        this.content = content;
    }
}
