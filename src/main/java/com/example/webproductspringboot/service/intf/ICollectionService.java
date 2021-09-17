package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.CollectionDto;

import java.util.List;

public interface ICollectionService {

    List<CollectionDto> findAll();

    CollectionDto findById(String id);

    CollectionDto save(CollectionDto dto);

}
