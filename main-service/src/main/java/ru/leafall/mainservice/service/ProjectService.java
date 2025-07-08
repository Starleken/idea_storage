package ru.leafall.mainservice.service;

import ru.leafall.mainservice.dto.project.ProjectCreateDto;
import ru.leafall.mainservice.dto.project.ProjectFullDto;
import ru.leafall.mainservice.dto.project.ProjectUpdateDto;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;

import java.util.Collection;
import java.util.List;

public interface ProjectService {
    PaginationResponse<ProjectFullDto> findAllByIds(Collection<Long> ids, PaginationParams params);
    PaginationResponse<ProjectFullDto> findAll(PaginationParams params);
    ProjectFullDto findById(Long id);
    ProjectFullDto create(ProjectCreateDto dto);
    ProjectFullDto update(ProjectUpdateDto dto);
    Long delete(Long id);
}
