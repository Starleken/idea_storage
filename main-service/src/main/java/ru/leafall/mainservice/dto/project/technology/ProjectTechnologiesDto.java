package ru.leafall.mainservice.dto.project.technology;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTechnologiesDto {
    private Long projectId;
    private List<String> technologies;
    private Long totalCount;
}
