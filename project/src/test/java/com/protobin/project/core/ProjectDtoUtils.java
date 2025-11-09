package com.protobin.project.core;

import com.protobin.project.dto.project.ProjectCreateDto;
import com.protobin.project.dto.project.ProjectUpdateDto;

import java.util.UUID;

import static com.protobin.project.core.FakerUtils.*;
import static com.protobin.project.core.ProjectEntityUtils.*;

public abstract class ProjectDtoUtils {

    public static ProjectCreateDto generateCreateDto() {
        return ProjectCreateDto.builder()
                .title(FAKER.name().title())
                .description(FAKER.lorem().characters())
                .concept(FAKER.lorem().characters())
                .visibleStatus(generateVisibleStatus())
                .build();
    }

    public static ProjectUpdateDto generateUpdateDto(UUID id) {
        return ProjectUpdateDto.builder()
                .id(id)
                .title(FAKER.name().title())
                .description(FAKER.lorem().characters())
                .concept(FAKER.lorem().characters())
                .visibleStatus(generateVisibleStatus())
                .build();
    }
}
