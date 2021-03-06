package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.ReviewDto;
import com.example.webproductspringboot.entity.ReviewImageEntity;
import com.example.webproductspringboot.entity.ReviewProductEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IReviewImageProductReponsitory;
import com.example.webproductspringboot.reponsitory.IReviewProductReponsitory;
import com.example.webproductspringboot.service.intf.IReviewProductService;
import com.example.webproductspringboot.utils.CookieUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewProductService extends AbstractService implements IReviewProductService {

    private final IReviewProductReponsitory _iReviewProductReponsitory;
    private final IReviewImageProductReponsitory _iReviewImageProductReponsitory;

    protected ReviewProductService(HttpServletRequest request, IReviewProductReponsitory iReviewProductReponsitory, IReviewImageProductReponsitory iReviewImageProductReponsitory) {
        super(request);
        _iReviewProductReponsitory = iReviewProductReponsitory;
        _iReviewImageProductReponsitory = iReviewImageProductReponsitory;
    }

    @Override
    public List<ReviewDto> findAll() {
        List<ReviewProductEntity> lst = _iReviewProductReponsitory.findAll(sortAZByCreated());
        return lst.stream().map(e -> (ReviewDto) map(e)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto save(ReviewDto dto) {
        ReviewProductEntity entity = (ReviewProductEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(true);
        entity.setCreated(new Date(System.currentTimeMillis()));
        entity.setIdVisit(userEntity);
        _iReviewProductReponsitory.save(entity);
        saveHistory(userEntity, "Th??m ????nh gi??", entity.toString());
        return (ReviewDto) map(entity);
    }

    @Override
    public ReviewDto update(ReviewDto dto) {
        ReviewProductEntity entity = (ReviewProductEntity) map(dto);
        if (entity == null)
            throw new BadRequestException(CookieUtils.get().errorsProperties(request, "lang", "data.not.found"));
        UserEntity userEntity = getUserLogin();
        Optional<ReviewProductEntity> optional = _iReviewProductReponsitory.findById(entity.getId());
        if (optional.isEmpty())
            throw new NotFoundException(CookieUtils.get().errorsProperties(request, "review", "review.not.found"));
        ReviewProductEntity fake = optional.get();
        if (entity.getStatus() == null) entity.setStatus(fake.getStatus());
        entity.setCreated(fake.getCreated());
        _iReviewProductReponsitory.save(entity);
        saveHistory(userEntity, "S???a ????nh gi??", fake + "\n" + entity);
        return (ReviewDto) map(entity);
    }

    @Override
    public void saveImageReview(String x, ReviewDto reviewDtoSave) {
        _iReviewImageProductReponsitory.save(ReviewImageEntity.builder()
                .id(UUID.randomUUID().toString())
                .idReviewProduct(ReviewProductEntity.builder().id(reviewDtoSave.getId()).build())
                .pathImage(x)
                .build());
    }
}
