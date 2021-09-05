package com.example.webproductspringboot.service;

import java.util.List;

public interface IGenericService<Dto, ID> {

    Dto saveDto(Dto dto);

    Dto newDto(Dto dto);

    Dto findById(ID id);

    List<Dto> findAll();

}
