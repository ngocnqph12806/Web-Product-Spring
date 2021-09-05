package com.example.webproductspringboot.model.dto;

import com.example.webproductspringboot.entity.AttributeEntity;
import com.example.webproductspringboot.utils.MapperModelUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDto {

    private String id;
    private String name;
    private String description;
    private Boolean status;
    private Date created;

    public static AttributeDto toDto(AttributeEntity entity) {
        AttributeDto attributeDto = (AttributeDto) MapperModelUtils.get().toDto(entity, AttributeDto.class);
        return attributeDto;
    }

}
