package ru.leafall.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leafall.mainservice.dto.project.related.RelatedProjectCreateDto;
import ru.leafall.mainservice.dto.project.related.RelatedProjectFullDto;
import ru.leafall.mainservice.dto.project.related.RelatedProjectShortDto;
import ru.leafall.mainservice.dto.project.related.RelatedProjectUpdateDto;
import ru.leafall.mainservice.entity.RelatedProjectEntity;
import ru.leafall.mainservice.mapper.RelatedProjectMapper;
import ru.leafall.mainservice.repository.ProjectRepository;
import ru.leafall.mainservice.repository.RelatedProjectRepository;
import ru.leafall.mainservice.service.RelatedProjectService;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;
import ru.leafall.mainstarter.utils.ThrowableUtils;

@Service
@RequiredArgsConstructor
public class RelatedProjectServiceImpl implements RelatedProjectService {

    private final RelatedProjectRepository repository;
    private final ProjectRepository projectRepository;
    private final RelatedProjectMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse<RelatedProjectShortDto> findAllByProjectId(Long projectId, PaginationParams params) {
        var sort = Sort.by("name").ascending();
        var pageable = PageRequest.of(params.page(), params.limit(), sort);
        var relatedProjects = repository.findAllByProjectId(projectId, pageable);
        var mappedRelatedProjects = relatedProjects.map(mapper::mapToShortDto);
        return new PaginationResponse<>(mappedRelatedProjects.getContent(), mappedRelatedProjects.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public RelatedProjectFullDto findById(Long id) {
        var relatedProject = findByIdOrThrowNotFoundException(id);
        return mapper.mapToFullDto(relatedProject);
    }

    @Override
    @Transactional
    public RelatedProjectShortDto create(RelatedProjectCreateDto dto) {
        var project = projectRepository.findById(dto.getProjectId()).orElseThrow(() ->
                ThrowableUtils.getBadRequestException("Project with id %d is not found", dto.getProjectId())
        );
        var entity = mapper.mapToEntity(dto);
        entity.setProject(project);

        var result = repository.save(entity);

        return mapper.mapToShortDto(result);
    }

    @Override
    @Transactional
    public RelatedProjectShortDto update(RelatedProjectUpdateDto dto) {
        var entity = findByIdOrThrowNotFoundException(dto.getId());
        if (entity.getName().equalsIgnoreCase(dto.getName()) ||
                entity.getLink().equalsIgnoreCase(dto.getLink())) {
            entity.setIsModerated(null);
        }
        var result = repository.save(entity);
        return mapper.mapToShortDto(result);
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        var relatedProject = findByIdOrThrowNotFoundException(id);
        repository.delete(relatedProject);
        return id;
    }

    private RelatedProjectEntity findByIdOrThrowNotFoundException(Long id) {
        return repository.findById(id).orElseThrow(() ->
                ThrowableUtils.getBadRequestException("Related project with id %d is not found")
        );
    }
}
