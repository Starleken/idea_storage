package ru.leafall.mainservice.dto.project.related;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatedProjectShortDto {
    private Long id;
    private String name;
    private Long projectId;
    private String link;
    private Boolean isModerated;
    private String strongSide;
    private String weakSide;
}
