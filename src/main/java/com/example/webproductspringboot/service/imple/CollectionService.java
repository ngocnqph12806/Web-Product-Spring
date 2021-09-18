package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.CollectionDto;
import com.example.webproductspringboot.entity.CategoryEntity;
import com.example.webproductspringboot.entity.CollectionEntity;
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
public class CollectionService extends AbstractService  implements ICollectionService {

    @Autowired
    private ICollectionReponsitory _iCollectionReponsitory;

    @Override
    public List<CollectionDto> findAll() {
//        return _iCollectionReponsitory.findAll().stream().map(CollectionDto::toDto).collect(Collectors.toList());
        return null;
    }

    @Override
    public CollectionDto findById(String idCollection) {
//        Optional<CollectionEntity> collectionEntity = _iCollectionReponsitory.findById(idCollection);
//        if (collectionEntity.isEmpty()) throw new NotFoundException("Không tìm thấy danh mục");
//        return CollectionDto.toDto(collectionEntity.get());
        return null;
    }

    @Override
    public CollectionDto save(CollectionDto dto) {
//        CollectionEntity collectionEntity = dto.toEntity();
//        if (collectionEntity.getId() == null || collectionEntity.getId().isBlank() || collectionEntity.getId().isEmpty()) {
//            collectionEntity = collectionEntity.toBuilder()
//                    .id(UUID.randomUUID().toString())
//                    .status(true)
//                    .created(new Date())
//                    .build();
//        } else {
//            Optional<CollectionEntity> entity = _iCollectionReponsitory.findById(collectionEntity.getId());
//            if (entity.isEmpty()) throw new NotFoundException("Danh mục không tồn tại");
//            else {
//                CollectionEntity fake = entity.get();
//                collectionEntity = collectionEntity.toBuilder()
//                        .description(fake.getDescription())
//                        .status(fake.getStatus())
//                        .created(fake.getCreated())
//                        .build();
//            }
//        }
//        collectionEntity = _iCollectionReponsitory.save(collectionEntity);
//        if (collectionEntity == null) {
//            throw new InternalServerException("Lưu không thành công");
//        }
//        return CollectionDto.toDto(collectionEntity);
        return null;
    }

    private Object toObj(Object data) {
        if (data == null) return null;
        if (data instanceof CollectionDto) {
            CollectionDto dto = (CollectionDto) data;
            return CollectionEntity.builder()
                    .build();
        } else if (data instanceof CategoryEntity) {
            CollectionEntity entity = (CollectionEntity) data;
            return CollectionDto.builder()
                    .build();
        }
        return null;
    }
}
