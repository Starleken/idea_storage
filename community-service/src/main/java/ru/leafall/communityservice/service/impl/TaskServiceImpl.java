package ru.leafall.communityservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leafall.communityservice.dto.MessageSenderDto;
import ru.leafall.communityservice.dto.task.TaskCreateDto;
import ru.leafall.communityservice.dto.task.TaskResponseFullDto;
import ru.leafall.communityservice.dto.task.TaskUpdateDto;
import ru.leafall.communityservice.entity.ParticipantEntity;
import ru.leafall.communityservice.entity.TaskEntity;
import ru.leafall.communityservice.mapper.TaskMapper;
import ru.leafall.communityservice.model.User;
import ru.leafall.communityservice.repository.ParticipantRepository;
import ru.leafall.communityservice.repository.TaskRepository;
import ru.leafall.communityservice.service.TaskService;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;
import ru.leafall.mainstarter.utils.ThrowableUtils;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final ParticipantRepository participantRepository;
    private final TaskRepository repository;
    private final AccountService accountService;
    private final MailSenderService mailSenderService;
    private final TaskMapper mapper;

    @Override
    @Transactional
    public PaginationResponse<TaskResponseFullDto> findAllByParticipantId(Long participantId, PaginationParams params) {
        var sort = Sort.by("id").ascending();
        var pagination = PageRequest.of(params.page(), params.limit(), sort);
        var participant = findParticipantByIdOrThrowNotFoundException(participantId);
        var entities = repository.findAllByParticipant(participant, pagination);
        var result = entities.map(mapper::mapToFullDto);
        return new PaginationResponse<>(result.getContent(), result.getTotalElements());
    }

    @Override
    @Transactional
    public TaskResponseFullDto findById(Long id) {
        var task = findTaskByIdOrThrowNotFoundException(id);
        return mapper.mapToFullDto(task);
    }

    @Override
    @Transactional
    public TaskResponseFullDto create(TaskCreateDto dto) {
        var entity = mapper.mapToEntity(dto);
        var result = repository.save(entity);
        return mapper.mapToFullDto(result);
    }

    @Override
    @Transactional
    public TaskResponseFullDto update(TaskUpdateDto dto) {
        var task = findTaskByIdOrThrowNotFoundException(dto.getId());
        mapper.update(task, dto);
        var result = repository.save(task);
        if (dto.getIsFinish()) {
            var participants = participantRepository.findAllByProjectId(task.getParticipant().getProjectId());
            var userIds = participants.stream().map(ParticipantEntity::getUserId).collect(Collectors.toSet());
            var users = accountService.findAllByIds(userIds);
            for (var user: users) {
                var messageSenderDto = new MessageSenderDto();
                messageSenderDto.setMessage(String.format("Задача в проекте под названием '%s' была выполнена", dto.getHeader()));
                messageSenderDto.setEmail(user.getEmail());
                mailSenderService.sendMessage(messageSenderDto);
            }
        }
        return mapper.mapToFullDto(result);
    }

    @Override
    @Transactional
    public TaskResponseFullDto delete(Long id) {
        var task = findTaskByIdOrThrowNotFoundException(id);
        repository.delete(task);
        return mapper.mapToFullDto(task);
    }

    private ParticipantEntity findParticipantByIdOrThrowNotFoundException(Long participantId) {
        return participantRepository.findById(participantId).orElseThrow(() ->
                ThrowableUtils.getNotFoundException("Participant with id %d is not found", participantId)
        );
    }

    private TaskEntity findTaskByIdOrThrowNotFoundException(Long id) {
        return repository.findById(id).orElseThrow(() ->
                ThrowableUtils.getNotFoundException("Task with id %d is not found", id)
        );
    }
}
