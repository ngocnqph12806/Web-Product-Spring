package com.example.webproductspringboot.dto.product;

import com.example.webproductspringboot.entity.ReviewProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private String id;
    private Integer point;
    private String idVisit;
    private String fullName;
    private String email;
    private String description;
    private Boolean introduce;
    private Date dateCreated;
    private List<String> lstPathImages;

    public static ReviewDto toDto(ReviewProductEntity entity) {
        if (entity == null) return null;
        return ReviewDto.builder()
                .id(entity.getId())
                .point(entity.getPoint())
                .idVisit(entity.getIdVisit().getId())
                .fullName(entity.getIdVisit().getFullName())
                .email(entity.getIdVisit().getEmail())
                .description(entity.getDescription())
                .introduce(entity.getIntroduce())
                .dateCreated(entity.getCreated())
                .lstPathImages(entity.getLstReviewImageEntities().stream().map(e -> e.getPathImage()).collect(Collectors.toList()))
                .build();
    }
}
