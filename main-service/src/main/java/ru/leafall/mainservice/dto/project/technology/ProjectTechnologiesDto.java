package ru.leafall.mainservice.dto.project.technology;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTechnologiesDto {
    private Long projectId;
    private Set<String> technologies = new HashSet<>();
    private Long totalCount;
}
