package com.protobin.project.service;

import com.protobin.project.dto.ProjectCreateDto;
import com.protobin.project.dto.ProjectResponseDto;
import com.protobin.project.dto.ProjectUpdateDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface ProjectService {

    ProjectResponseDto findById(UUID id);

    ProjectResponseDto create(ProjectCreateDto createDto);

    ProjectResponseDto update(ProjectUpdateDto updateDto);

    void deleteById(UUID id);
}
