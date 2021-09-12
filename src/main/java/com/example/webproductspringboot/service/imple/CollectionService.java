package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.dto.product.IntroCollectionAdminDto;
import com.example.webproductspringboot.entity.CollectionEntity;
import com.example.webproductspringboot.exception.NotFoundException;
import com.example.webproductspringboot.reponsitory.ICollectionReponsitory;
import com.example.webproductspringboot.service.intf.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
}
