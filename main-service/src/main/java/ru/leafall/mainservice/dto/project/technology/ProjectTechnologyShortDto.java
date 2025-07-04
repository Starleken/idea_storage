package ru.leafall.mainservice.dto.project.technology;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTechnologyShortDto {
    private Long technologyId;
    private String name;
}
