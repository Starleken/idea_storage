package ru.leafall.communityservice.mapper;

import org.mapstruct.Mapper;
import ru.leafall.communityservice.dto.task.TaskCreateDto;
import ru.leafall.communityservice.dto.task.TaskResponseFullDto;
import ru.leafall.communityservice.dto.task.TaskResponseShortDto;
import ru.leafall.communityservice.dto.task.TaskUpdateDto;
import ru.leafall.communityservice.entity.TaskEntity;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskResponseFullDto mapToFullDto(TaskEntity task);
    TaskResponseShortDto mapToShortDto(TaskEntity task);
    TaskEntity mapToEntity(TaskCreateDto dto);
    default void update(TaskEntity task, TaskUpdateDto dto) {
        if (task == null || dto == null) {
            return;
        }
        task.setHeader(dto.getHeader());
        task.setDescription(dto.getDescription());
        task.setExpiredAt(dto.getExpiredAt());
        task.setFinishedAt(dto.getIsFinish() ? Instant.now().toEpochMilli() : null);

    }
}
