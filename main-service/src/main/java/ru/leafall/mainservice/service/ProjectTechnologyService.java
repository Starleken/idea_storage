package ru.leafall.mainservice.service;

import ru.leafall.mainservice.dto.project.technology.ProjectTechnologiesDto;
import ru.leafall.mainservice.dto.project.technology.ProjectTechnologyCreateDto;
import ru.leafall.mainservice.dto.project.technology.ProjectTechnologyShortDto;

public interface ProjectTechnologyService {
     ProjectTechnologiesDto findAllTechnologiesByProjectId(Long projectId);
     ProjectTechnologyShortDto create(ProjectTechnologyCreateDto dto);
     void delete(Long projectId, Integer technologyId);
}
