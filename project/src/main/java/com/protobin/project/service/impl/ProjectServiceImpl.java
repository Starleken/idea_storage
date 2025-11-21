package com.protobin.project.service.impl;

import com.protobin.project.dto.project.ProjectCreateDto;
import com.protobin.project.dto.project.ProjectResponseDto;
import com.protobin.project.dto.project.ProjectUpdateDto;
import com.protobin.project.entity.ProjectEntity;
import com.protobin.project.exception.NotFoundException;
import com.protobin.project.helper.PaginationParams;
import com.protobin.project.helper.PaginationResponse;
import com.protobin.project.mapper.ProjectMapper;
import com.protobin.project.repository.ProjectRepository;
import com.protobin.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    public PaginationResponse<ProjectResponseDto> findAllByTags(List<String> tagsNames, PaginationParams pagination) {
        var sort = Sort.by("id").ascending();
        var pageable = PageRequest.of(pagination.page(), pagination.limit(), sort);

        if (tagsNames == null || tagsNames.isEmpty()) {
            return getPaginationResponse(pageable);
        }

        var normalized = tagsNames.stream()
                .filter(Objects::nonNull)
                .map(name -> name.toLowerCase(Locale.ROOT))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        if (normalized.isEmpty()) {
            return getPaginationResponse(pageable);
        }

        var filtered = projectRepository.findAllByTagsIgnoreCase(normalized, normalized.size(), pageable);
        return new PaginationResponse<>(filtered.map(projectMapper::mapToDto).getContent(), filtered.getTotalElements());
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

    private PaginationResponse<ProjectResponseDto> getPaginationResponse(Pageable pageable) {
        var projects = projectRepository.findAll(pageable);
        return new PaginationResponse<>(projects.map(projectMapper::mapToDto).getContent(),
                projects.getTotalElements());
    }
}
