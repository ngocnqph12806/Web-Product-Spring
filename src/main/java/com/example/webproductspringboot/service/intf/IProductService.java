package com.example.webproductspringboot.service.intf;

import com.example.webproductspringboot.dto.product.*;

import java.util.List;
import java.util.Set;

public interface IProductService  {

    List<IntroProductWebDto> findBestSallers();

    List<IntroProductWebDto> findAllIntroWeb();

    List<IntroProductAdminDto> findAllIntroAdmin();

    List<IntroProductWebDto> findByIdCategory(String idCategory);

    DetailsProductDto findByPath(Long idUrl,String pathUrl);

    Set<String> getSetCategoryDetailsByIdCategory(String idCategory);

    Set<String> getSetColorByIdCategory(String idCategory);

    FilterPriceProductDto getMinMaxPrice();

    IntroProductWebDto findIntroById(String id);

    FormProductDto findFormById(String id);

    IntroProductWebDto save(FormProductDto dto);

    boolean changeStatus(String id, Boolean reuslt);
}
