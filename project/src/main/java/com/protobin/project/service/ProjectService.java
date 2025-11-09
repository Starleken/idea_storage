package com.protobin.project.service;

import com.protobin.project.dto.project.ProjectCreateDto;
import com.protobin.project.dto.project.ProjectResponseDto;
import com.protobin.project.dto.project.ProjectUpdateDto;

import java.util.UUID;

public interface ProjectService {

    ProjectResponseDto findById(UUID id);

    ProjectResponseDto create(ProjectCreateDto createDto);

    ProjectResponseDto update(ProjectUpdateDto updateDto);

    void deleteById(UUID id);
}
