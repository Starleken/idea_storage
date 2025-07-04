package ru.leafall.mainservice.mapper;

import ru.leafall.mainservice.dto.project.related.RelatedProjectCreateDto;
import ru.leafall.mainservice.dto.project.related.RelatedProjectFullDto;
import ru.leafall.mainservice.dto.project.related.RelatedProjectShortDto;
import ru.leafall.mainservice.dto.project.related.RelatedProjectUpdateDto;
import ru.leafall.mainservice.entity.RelatedProjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProjectMapper.class})
public interface RelatedProjectMapper {
    RelatedProjectFullDto mapToFullDto(RelatedProjectEntity entity);
    RelatedProjectShortDto mapToShortDto(RelatedProjectEntity entity);

    RelatedProjectEntity mapToEntity(RelatedProjectCreateDto dto);

    default void update(RelatedProjectEntity entity, RelatedProjectUpdateDto dto) {
        entity.setName(dto.getName());
        entity.setLink(dto.getLink());
        entity.setStrongSide(dto.getStrongSide());
        entity.setWeakSide(dto.getWeakSide());
        entity.setIsModerated(dto.getIsModerated());
    }
}
