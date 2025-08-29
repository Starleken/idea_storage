package ru.leafall.communityservice.mapper;

import org.mapstruct.Mapper;
import ru.leafall.communityservice.dto.participant.ParticipantCreateDto;
import ru.leafall.communityservice.dto.participant.ParticipantResponseFullDto;
import ru.leafall.communityservice.dto.participant.ParticipantUpdateDto;
import ru.leafall.communityservice.entity.ParticipantEntity;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
    ParticipantEntity mapToEntity(ParticipantCreateDto dto);
    ParticipantResponseFullDto mapToFullDto(ParticipantEntity entity);
    default void update(ParticipantEntity entity, ParticipantUpdateDto dto) {
        if (entity == null || dto == null) {
            return;
        }
        entity.setIsPrivileged(dto.getIsPrivileged());
    }
}
