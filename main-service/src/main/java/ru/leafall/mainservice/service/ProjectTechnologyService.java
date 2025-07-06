package ru.leafall.mainservice.service;

import ru.leafall.mainservice.dto.project.technology.ProjectTechnologiesDto;
import ru.leafall.mainservice.dto.project.technology.ProjectTechnologyCreateDto;
import ru.leafall.mainservice.dto.project.technology.ProjectTechnologyShortDto;
import ru.leafall.mainstarter.utils.PaginationParams;

public interface ProjectTechnologyService {
     ProjectTechnologiesDto findAllTechnologiesByProjectId(Long projectId, PaginationParams params);
     ProjectTechnologyShortDto create(ProjectTechnologyCreateDto dto);
     void delete(Long projectId, Integer technologyId);
}
