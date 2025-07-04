package ru.leafall.mainservice.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectFullDto {
    private Long id;

    private String name;

    private String idea;

    private String shortDescription;

    private String fullDescription;

    private String reasonForPurchase;

    private Double price;

    private Long createdAt;

    private Long expiredAt;

    private Long finishedAt;

    private Boolean isVisible;
}
