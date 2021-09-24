package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.ReviewDto;
import com.example.webproductspringboot.entity.ReviewProductEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.BadRequestException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.IReviewProductReponsitory;
import com.example.webproductspringboot.service.intf.IReviewProductService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewProductService extends AbstractService implements IReviewProductService {

    private final IReviewProductReponsitory _iReviewProductReponsitory;

    protected ReviewProductService(HttpServletRequest request, IReviewProductReponsitory iReviewProductReponsitory) {
        super(request);
        _iReviewProductReponsitory = iReviewProductReponsitory;
    }

    @Override
    public ReviewDto save(ReviewDto dto) {
        ReviewProductEntity entity = (ReviewProductEntity) map(dto);
        if (entity == null) throw new BadRequestException("Lỗi dữ liệu");
        UserEntity userEntity = getUserLogin();
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(true);
        entity.setCreated(new Date(System.currentTimeMillis()));
        _iReviewProductReponsitory.save(entity);
        saveHistory(userEntity, "Thêm đánh giá: \n" + entity);
        return (ReviewDto) map(entity);
    }

    @Override
    public ReviewDto update(ReviewDto dto) {
        ReviewProductEntity entity = (ReviewProductEntity) map(dto);
        if (entity == null) throw new BadRequestException("Lỗi dữ liệu");
        UserEntity userEntity = getUserLogin();
        Optional<ReviewProductEntity> optional = _iReviewProductReponsitory.findById(entity.getId());
        if (optional.isEmpty()) throw new NotFoundException("Đánh giá không tồn tại");
        ReviewProductEntity fake = optional.get();
        if (entity.getStatus() == null) entity.setStatus(fake.getStatus());
        entity.setCreated(fake.getCreated());
        _iReviewProductReponsitory.save(entity);
        saveHistory(userEntity, "Sửa đánh giá: \n" + fake + "\n" + entity);
        return (ReviewDto) map(entity);
    }
}
