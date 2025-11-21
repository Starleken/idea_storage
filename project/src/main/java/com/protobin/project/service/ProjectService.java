package com.protobin.project.service;

import com.protobin.project.dto.project.ProjectCreateDto;
import com.protobin.project.dto.project.ProjectResponseDto;
import com.protobin.project.dto.project.ProjectUpdateDto;
import com.protobin.project.helper.PaginationParams;
import com.protobin.project.helper.PaginationResponse;

import java.util.List;
import java.util.UUID;

public interface ProjectService {

    ProjectResponseDto findById(UUID id);

    PaginationResponse<ProjectResponseDto> findAllByTags(List<String> tagsNames, PaginationParams pagination);

    ProjectResponseDto create(ProjectCreateDto createDto);

    ProjectResponseDto update(ProjectUpdateDto updateDto);

    void deleteById(UUID id);
}
