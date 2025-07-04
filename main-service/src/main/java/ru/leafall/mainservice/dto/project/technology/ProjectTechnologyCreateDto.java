package ru.leafall.mainservice.dto.project.technology;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTechnologyCreateDto {
    @Min(0)
    @NotNull
    private Long projectId;

    @Min(0)
    @NotNull
    private String technology;
}
