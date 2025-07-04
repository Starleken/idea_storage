package ru.leafall.mainservice.dto.project;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class ProjectUpdateDto {
    @Min(0)
    @NotNull
    private Long id;

    @NotNull
    @Length(min = 1, max = 80)
    private String name;

    @NotNull
    @Length(min = 1, max = 255)
    private String idea;

    @NotNull
    @Length(min = 1, max = 500)
    private String shortDescription;

    @NotNull
    @Length(min = 1, max = 10000)
    private String fullDescription;

    @NotNull
    @Length(min = 1, max = 100)
    private String reasonForPurchase;

    @Min(0)
    @NotNull
    private Double price;

    @NotNull
    private Long expiredAt;

    private Long finishedAt;
    @NotNull
    private Boolean isVisible;
}
