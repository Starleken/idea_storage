package com.protobin.project.dto;

import com.protobin.project.entity.ProjectVisibleStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProjectUpdateDto {

    private UUID id;
    private String title;
    private String concept;
    private String description;
    private ProjectVisibleStatus visibleStatus;
}
