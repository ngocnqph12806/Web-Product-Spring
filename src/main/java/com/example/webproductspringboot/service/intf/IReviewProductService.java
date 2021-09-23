package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.ReviewDto;
import org.springframework.stereotype.Service;

public interface IReviewProductService {

    ReviewDto save(ReviewDto dto);

    ReviewDto update(ReviewDto dto);

}
