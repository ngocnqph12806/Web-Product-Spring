package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.product.IntroCollectionAdminDto;
import com.example.webproductspringboot.dto.staff.IntroStaffAdminDto;
import com.example.webproductspringboot.entity.CollectionEntity;
import com.example.webproductspringboot.entity.UserEntity;
import com.example.webproductspringboot.exception.InternalServerException;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.ICollectionReponsitory;
import com.example.webproductspringboot.service.intf.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CollectionService implements ICollectionService {

    @Autowired
    private ICollectionReponsitory _iCollectionReponsitory;

    @Override
    public List<IntroCollectionAdminDto> findAllIntroCollectionsAdmin() {
        return _iCollectionReponsitory.findAll().stream().map(IntroCollectionAdminDto::toDto).collect(Collectors.toList());
    }

    @Override
    public IntroCollectionAdminDto findIntroCollectionById(String idCollection) {
        Optional<CollectionEntity> collectionEntity = _iCollectionReponsitory.findById(idCollection);
        if (collectionEntity.isEmpty()) throw new NotFoundException("Không tìm thấy danh mục");
        return IntroCollectionAdminDto.toDto(collectionEntity.get());
    }

    @Override
    public IntroCollectionAdminDto saveCollection(IntroCollectionAdminDto dto) {
        CollectionEntity collectionEntity = dto.toEntity();
        if (collectionEntity.getId() == null || collectionEntity.getId().isBlank() || collectionEntity.getId().isEmpty()) {
            collectionEntity = collectionEntity.toBuilder()
                    .id(UUID.randomUUID().toString())
                    .status(true)
                    .created(new Date())
                    .build();
        } else {
            Optional<CollectionEntity> entity = _iCollectionReponsitory.findById(collectionEntity.getId());
            if (entity.isEmpty()) throw new NotFoundException("Danh mục không tồn tại");
            else {
                CollectionEntity fake = entity.get();
                collectionEntity = collectionEntity.toBuilder()
                        .description(fake.getDescription())
                        .status(fake.getStatus())
                        .created(fake.getCreated())
                        .build();
            }
        }
        collectionEntity = _iCollectionReponsitory.save(collectionEntity);
        if (collectionEntity == null) {
            throw new InternalServerException("Lưu không thành công");
        }
        return IntroCollectionAdminDto.toDto(collectionEntity);
    }
}
