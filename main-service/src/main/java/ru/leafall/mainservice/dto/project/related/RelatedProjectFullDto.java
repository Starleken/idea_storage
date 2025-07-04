package ru.leafall.mainservice.dto.project.related;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import ru.leafall.mainservice.dto.project.ProjectFullDto;
import ru.leafall.mainservice.entity.ProjectEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatedProjectFullDto {
    private Long id;
    private String name;
    private ProjectFullDto project;
    private String link;
    private Boolean isModerated;
    private String strongSide;
    private String weakSide;
}
