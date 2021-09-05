package com.example.webproductspringboot.service.imple;

import com.example.webproductspringboot.model.dto.CollectionDto;
import com.example.webproductspringboot.service.intf.ICollectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService implements ICollectionService {
    @Override
    public CollectionDto saveDto(CollectionDto collectionDto) {
        return null;
    }

    @Override
    public CollectionDto newDto(CollectionDto collectionDto) {
        return null;
    }

    @Override
    public CollectionDto findById(String s) {
        return null;
    }

    @Override
    public List<CollectionDto> findAll() {
        return null;
    }
}
