package ru.leafall.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leafall.mainservice.component.ProjectObservable;
import ru.leafall.mainservice.component.communication.CommunicationService;
import ru.leafall.mainservice.dto.project.ProjectCreateDto;
import ru.leafall.mainservice.dto.project.ProjectFullDto;
import ru.leafall.mainservice.dto.project.ProjectUpdateDto;
import ru.leafall.mainservice.entity.ProjectEntity;
import ru.leafall.mainservice.mapper.ProjectMapper;
import ru.leafall.mainservice.repository.ProjectRepository;
import ru.leafall.mainservice.service.ProjectService;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;
import ru.leafall.mainstarter.utils.ThrowableUtils;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final ProjectMapper mapper;
    private final Set<ProjectObservable> projects;

    @Autowired
    public ProjectServiceImpl(ProjectRepository repository, ProjectMapper mapper, CommunicationService service) {
        this.repository = repository;
        this.mapper = mapper;
        this.projects = new HashSet<>();
        projects.add(service);
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse<ProjectFullDto> findAllByIds(Collection<Long> ids, PaginationParams params) {
        final var isVisible = true;
        var sort = Sort.by("name").ascending();
        var pageable = PageRequest.of(params.page(), params.limit(), sort);
        var result = repository.findAllByIdInAndIsVisible(ids, isVisible, pageable);
        var mappedResult = result.map(mapper::mapToFullDto);
        return new PaginationResponse<>(mappedResult.getContent(), mappedResult.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse<ProjectFullDto> findAll(PaginationParams params) {
        final var isVisible = true;
        var sort = Sort.by("name").ascending();
        var pageable = PageRequest.of(params.page(), params.limit(), sort);
        var result = repository.findAllByIsVisible(isVisible, pageable);
        var mappedResult = result.map(mapper::mapToFullDto);
        return new PaginationResponse<>(mappedResult.getContent(), mappedResult.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectFullDto findById(Long id) {
        ProjectEntity result = findProjectOrThrowNotFoundException(id);
        return mapper.mapToFullDto(result);
    }

    @Override
    @Transactional
    public ProjectFullDto create(ProjectCreateDto dto) {
        var project = mapper.mapToEntity(dto);
        var result = repository.save(project);
        return mapper.mapToFullDto(result);
    }

    @Override
    @Transactional
    public ProjectFullDto update(ProjectUpdateDto dto) {
        var project = findProjectOrThrowNotFoundException(dto.getId());
        mapper.update(project, dto);
        var result = repository.save(project);
        return mapper.mapToFullDto(result);
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        var project = findProjectOrThrowNotFoundException(id);
        repository.delete(project);
        return id;
    }

    private ProjectEntity findProjectOrThrowNotFoundException(Long id) {
        return repository.findById(id).orElseThrow(() ->
                ThrowableUtils.getNotFoundException("Project with id is not found", id)
        );
    }
}
