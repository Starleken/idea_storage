package ru.leafall.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leafall.mainservice.dto.project.technology.ProjectTechnologiesDto;
import ru.leafall.mainservice.dto.project.technology.ProjectTechnologyCreateDto;
import ru.leafall.mainservice.dto.project.technology.ProjectTechnologyShortDto;
import ru.leafall.mainservice.entity.ProjectEntity;
import ru.leafall.mainservice.entity.ProjectTechnologyEntity;
import ru.leafall.mainservice.entity.TechnologyEntity;
import ru.leafall.mainservice.entity.primarykeys.ProjectTechnologyPK;
import ru.leafall.mainservice.repository.ProjectRepository;
import ru.leafall.mainservice.repository.ProjectTechnologyRepository;
import ru.leafall.mainservice.repository.TechnologyRepository;
import ru.leafall.mainservice.service.ProjectTechnologyService;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.ThrowableUtils;

@Service
@RequiredArgsConstructor
public class ProjectTechnologyServiceImpl implements ProjectTechnologyService {

    private final ProjectTechnologyRepository repository;
    private final TechnologyRepository technologyRepository;
    private final ProjectRepository projectRepository;


    @Override
    @Transactional
    public ProjectTechnologiesDto findAllTechnologiesByProjectId(Long projectId, PaginationParams params) {
        var sort = Sort.by("technologyId").ascending();
        var pageable = PageRequest.of(params.page(), params.limit(), sort);
        var items = repository.findAllByProjectId(projectId, pageable);
        var result = new ProjectTechnologiesDto();
        result.setProjectId(projectId);
        for (var item: items) {
            result.getTechnologies().add(item.getId().getTechnology().getName());
        }
        result.setTotalCount(items.getTotalElements());
        return result;
    }

    @Override
    @Transactional
    public ProjectTechnologyShortDto create(ProjectTechnologyCreateDto dto) {
        var project = findProjectOrThrowNotFoundException(dto.getProjectId());
        TechnologyEntity technology;
        var optionalTechnology = technologyRepository.findByName(dto.getTechnology().toLowerCase().trim());

        if(optionalTechnology.isEmpty()) {
            var newTechnology = new TechnologyEntity();
            technology = technologyRepository.save(newTechnology);
        } else {
            technology = optionalTechnology.get();
        }

        var key = new ProjectTechnologyPK();
        key.setProject(project);
        key.setTechnology(technology);

        var technologyEntity = new ProjectTechnologyEntity();
        technologyEntity.setId(key);

        repository.save(technologyEntity);
        var result = new ProjectTechnologyShortDto();
        result.setTechnologyId(technology.getId());
        result.setName(technology.getName());
        return result;
    }

    @Override
    @Transactional
    public void delete(Long projectId, Integer technologyId) {
        var project = findProjectOrThrowNotFoundException(projectId);
        var technology = technologyRepository.findById(technologyId).orElseThrow(() ->
                ThrowableUtils.getNotFoundException("Technology with id %d is not found", technologyId)
        );
        var key = new ProjectTechnologyPK();
        key.setTechnology(technology);
        key.setProject(project);
        repository.deleteById(key);
    }

    private ProjectEntity findProjectOrThrowNotFoundException(Long id) {
        return projectRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.getNotFoundException("Project with id is not found", id)
        );
    }
}
