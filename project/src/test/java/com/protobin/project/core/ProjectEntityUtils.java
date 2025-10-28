package com.protobin.project.core;

import com.protobin.project.entity.ProjectEntity;
import com.protobin.project.entity.ProjectVisibleStatus;

import java.util.Random;

import static com.protobin.project.core.FakerUtils.*;

public abstract class ProjectEntityUtils {

    public static ProjectEntity generateProject() {
        var entity = new ProjectEntity();
        entity.setTitle(FAKER.name().title());
        entity.setConcept(FAKER.lorem().characters());
        entity.setDescription(FAKER.lorem().characters());
        entity.setVisibleStatus(generateVisibleStatus());

        return entity;
    }

    public static ProjectVisibleStatus generateVisibleStatus() {
        var random = new Random();
        var visibleIndex = random.nextInt(ProjectVisibleStatus.values().length);

        return ProjectVisibleStatus.values()[visibleIndex];
    }
}
