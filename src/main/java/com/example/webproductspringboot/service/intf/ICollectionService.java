package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.product.IntroCollectionAdminDto;

import java.util.List;

public interface ICollectionService  {
    List<IntroCollectionAdminDto> findAllIntroCollectionsAdmin();

    IntroCollectionAdminDto findIntroCollectionById(String idCollection);

    IntroCollectionAdminDto saveCollection(IntroCollectionAdminDto dto);
}
