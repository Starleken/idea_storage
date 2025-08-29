package ru.leafall.communityservice.mapper;

import org.mapstruct.Mapper;
import ru.leafall.communityservice.dto.step.StepCreateDto;
import ru.leafall.communityservice.dto.step.StepFullDto;
import ru.leafall.communityservice.dto.step.StepUpdateDto;
import ru.leafall.communityservice.entity.StepEntity;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface StepMapper {
    StepEntity mapToEntity(StepCreateDto dto);
    StepFullDto mapToFullDto(StepEntity entity);
    default void update(StepEntity entity, StepUpdateDto dto) {
        if (entity == null || dto == null) {
            return;
        }
        entity.setText(dto.getText());
        entity.setFinishedAt(dto.getIsFinished() ? Instant.now().toEpochMilli() : null);
    }
}
