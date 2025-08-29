package ru.leafall.mainservice.component;

import ru.leafall.mainservice.entity.ProjectEntity;

public interface ProjectObservable {
    void create(ProjectEntity project);
    void delete(ProjectEntity project);
}
