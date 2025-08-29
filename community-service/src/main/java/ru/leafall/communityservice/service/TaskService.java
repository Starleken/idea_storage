package ru.leafall.communityservice.service;

import ru.leafall.communityservice.dto.task.TaskCreateDto;
import ru.leafall.communityservice.dto.task.TaskResponseFullDto;
import ru.leafall.communityservice.dto.task.TaskResponseShortDto;
import ru.leafall.communityservice.dto.task.TaskUpdateDto;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;

public interface TaskService {
    PaginationResponse<TaskResponseFullDto> findAllByParticipantId(Long participantId, PaginationParams params);
    TaskResponseFullDto findById(Long id);
    TaskResponseFullDto create(TaskCreateDto dto);
    TaskResponseFullDto update(TaskUpdateDto dto);
    TaskResponseFullDto delete(Long id);
}
