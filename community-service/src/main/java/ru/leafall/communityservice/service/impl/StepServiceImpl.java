package ru.leafall.communityservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leafall.communityservice.dto.step.StepCreateDto;
import ru.leafall.communityservice.dto.step.StepFullDto;
import ru.leafall.communityservice.dto.step.StepUpdateDto;
import ru.leafall.communityservice.entity.StepEntity;
import ru.leafall.communityservice.entity.TaskEntity;
import ru.leafall.communityservice.mapper.StepMapper;
import ru.leafall.communityservice.repository.StepRepository;
import ru.leafall.communityservice.repository.TaskRepository;
import ru.leafall.communityservice.service.StepService;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;
import ru.leafall.mainstarter.utils.ThrowableUtils;

@Service
@RequiredArgsConstructor
public class StepServiceImpl implements StepService {

    private final TaskRepository taskRepository;
    private final StepRepository stepRepository;
    private final StepMapper mapper;

    @Override
    @Transactional
    public PaginationResponse<StepFullDto> findAllByTaskId(Long taskId, PaginationParams params) {
        var task = findTaskByIdOrThrowNotFoundException(taskId);
        var sort = Sort.by("id").ascending();
        var pagination = PageRequest.of(params.page(), params.limit(), sort);
        var entities = stepRepository.findAllByTaskId(task.getId(), pagination);
        var result = entities.map(mapper::mapToFullDto);
        return new PaginationResponse<>(result.getContent(), result.getTotalElements());
    }

    @Override
    @Transactional
    public StepFullDto findById(Long id) {
        var step = findStepByIdOrThrowNotFoundException(id);
        return mapper.mapToFullDto(step);
    }

    @Override
    @Transactional
    public StepFullDto create(StepCreateDto dto) {
        var entity = mapper.mapToEntity(dto);
        var result = stepRepository.save(entity);
        return mapper.mapToFullDto(result);
    }

    @Override
    @Transactional
    public StepFullDto update(StepUpdateDto dto) {
        var entity = findStepByIdOrThrowNotFoundException(dto.getId());
        mapper.update(entity, dto);
        var result = stepRepository.save(entity);
        return mapper.mapToFullDto(result);
    }

    @Override
    @Transactional
    public StepFullDto delete(Long id) {
        var entity = findStepByIdOrThrowNotFoundException(id);
        stepRepository.delete(entity);
        return mapper.mapToFullDto(entity);
    }

    private StepEntity findStepByIdOrThrowNotFoundException(Long id) {
        return stepRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.getNotFoundException("Step with id %d is not found", id)
        );
    }

    private TaskEntity findTaskByIdOrThrowNotFoundException(Long id) {
        return taskRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.getNotFoundException("Task with id %d is not found", id)
        );
    }
}
