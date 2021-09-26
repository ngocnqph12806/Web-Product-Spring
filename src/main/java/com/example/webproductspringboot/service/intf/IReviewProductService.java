package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.ReviewDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IReviewProductService {
    List<ReviewDto> findAll();

    ReviewDto save(ReviewDto dto);

    ReviewDto update(ReviewDto dto);

}
