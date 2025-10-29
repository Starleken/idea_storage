package com.protobin.project.dto;

import com.protobin.project.entity.ProjectVisibleStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectCreateDto {

    private String title;
    private String concept;
    private String description;
    private ProjectVisibleStatus visibleStatus;
}
