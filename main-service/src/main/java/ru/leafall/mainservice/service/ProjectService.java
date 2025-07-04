package ru.leafall.mainservice.service;

import ru.leafall.mainservice.dto.project.ProjectCreateDto;
import ru.leafall.mainservice.dto.project.ProjectFullDto;
import ru.leafall.mainservice.dto.project.ProjectUpdateDto;

import java.util.Collection;
import java.util.List;

public interface ProjectService {
    List<ProjectFullDto> findAllByIds(Collection<Long> ids);
    List<ProjectFullDto> findAll();
    ProjectFullDto findById(Long id);
    ProjectFullDto create(ProjectCreateDto dto);
    ProjectFullDto update(ProjectUpdateDto dto);
    Long delete(Long id);
}
