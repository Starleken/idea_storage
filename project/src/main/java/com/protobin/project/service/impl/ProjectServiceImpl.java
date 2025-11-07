package com.protobin.project.service.impl;

import com.protobin.project.dto.ProjectCreateDto;
import com.protobin.project.dto.ProjectResponseDto;
import com.protobin.project.dto.ProjectUpdateDto;
import com.protobin.project.entity.ProjectEntity;
import com.protobin.project.exception.NotFoundException;
import com.protobin.project.mapper.ProjectMapper;
import com.protobin.project.repository.ProjectRepository;
import com.protobin.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    @Transactional(readOnly = true)
    public ProjectResponseDto findById(UUID id) {
        var found = getById(id);

        return projectMapper.mapToDto(found);
    }

    @Override
    @Transactional
    public ProjectResponseDto create(ProjectCreateDto createDto) {
        var toSave = projectMapper.mapToEntity(createDto);
        var saved = projectRepository.save(toSave);

        return projectMapper.mapToDto(saved);
    }

    @Override
    @Transactional
    public ProjectResponseDto update(ProjectUpdateDto updateDto) {
        var toUpdate = getById(updateDto.getId());

        projectMapper.update(toUpdate, updateDto);
        var updated = projectRepository.save(toUpdate);

        return projectMapper.mapToDto(updated);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        var toDelete = getById(id);
        toDelete.setDeletedAt(new Date().getTime());
        projectRepository.save(toDelete);
    }

    private ProjectEntity getById(UUID id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project is not found"));
    }
}
