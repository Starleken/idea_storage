package ru.leafall.mainservice.mapper;

import ru.leafall.mainservice.dto.technology.TechnologyCreateDto;
import ru.leafall.mainservice.dto.technology.TechnologyFullDto;
import ru.leafall.mainservice.dto.technology.TechnologyUpdateDto;
import ru.leafall.mainservice.entity.TechnologyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnologyMapper {
    TechnologyFullDto mapToFullDto(TechnologyEntity entity);
    TechnologyEntity mapToEntity(TechnologyCreateDto dto);
    default void update(TechnologyEntity entity, TechnologyUpdateDto dto) {
        entity.setName(dto.getName());
    }
}
