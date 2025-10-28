package com.protobin.project.dto;

import com.protobin.project.entity.ProjectVisibleStatus;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class ProjectResponseDto {

    private UUID id;
    private String title;
    private String concept;
    private String description;
    private ProjectVisibleStatus visibleStatus;
}
