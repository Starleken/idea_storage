package ru.leafall.communityservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leafall.communityservice.dto.participant.ParticipantCreateDto;
import ru.leafall.communityservice.dto.participant.ParticipantResponseFullDto;
import ru.leafall.communityservice.dto.participant.ParticipantUpdateDto;
import ru.leafall.communityservice.entity.ParticipantEntity;
import ru.leafall.communityservice.mapper.ParticipantMapper;
import ru.leafall.communityservice.repository.ParticipantRepository;
import ru.leafall.communityservice.service.ParticipantService;
import ru.leafall.mainstarter.model.Project;
import ru.leafall.mainstarter.service.ProjectService;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;
import ru.leafall.mainstarter.utils.ThrowableUtils;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository repository;
    private final ParticipantMapper mapper;
    private final ProjectService projectService;

    @Override
    @Transactional
    public PaginationResponse<Project> findAllByUserId(Long userId, PaginationParams params) {
        var sort = Sort.by("id").ascending();
        var pagination = PageRequest.of(params.page(), params.limit(), sort);
        var paginationResult = repository.findAllByUserId(userId, pagination);
        var projects = projectService.findAllByIds(paginationResult.map(ParticipantEntity::getProjectId).toSet());
        return new PaginationResponse<>(projects, paginationResult.getTotalElements());
    }

    @Override
    @Transactional
    public ParticipantResponseFullDto findById(Long id) {
        var participant = findByIdOrThrowNotFoundException(id);
        return mapper.mapToFullDto(participant);
    }

    @Override
    @Transactional
    public ParticipantResponseFullDto create(ParticipantCreateDto dto) {
        var entity = mapper.mapToEntity(dto);
        entity.setIsPrivileged(true);
        var result = repository.save(entity);
        return mapper.mapToFullDto(result);
    }

    @Override
    @Transactional
    public ParticipantResponseFullDto update(ParticipantUpdateDto dto) {
        var entity = findByIdOrThrowNotFoundException(dto.getId());
        mapper.update(entity, dto);
        var result = repository.save(entity);
        return mapper.mapToFullDto(result);
    }

    @Override
    @Transactional
    public ParticipantResponseFullDto delete(Long id) {
        var entity = findByIdOrThrowNotFoundException(id);
        repository.delete(entity);
        return mapper.mapToFullDto(entity);
    }

    private ParticipantEntity findByIdOrThrowNotFoundException(Long id) {
        return repository.findById(id).orElseThrow(() ->
                ThrowableUtils.getNotFoundException("Participant with id %d is not found", id)
        );
    }
}
