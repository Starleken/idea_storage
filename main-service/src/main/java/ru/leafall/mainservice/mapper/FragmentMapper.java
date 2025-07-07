package ru.leafall.mainservice.mapper;

import ru.leafall.mainservice.dto.fragment.FragmentCreateDto;
import ru.leafall.mainservice.dto.fragment.FragmentFullDto;
import ru.leafall.mainservice.dto.fragment.FragmentShortDto;
import ru.leafall.mainservice.dto.fragment.FragmentUpdateDto;
import ru.leafall.mainservice.entity.FragmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProjectMapper.class})
public interface FragmentMapper {
    FragmentFullDto mapToFullDto(FragmentEntity fragment);

    FragmentShortDto mapToShortDto(FragmentEntity fragment);
    @Mapping(target = "picture", ignore = true)
    FragmentEntity mapToEntity(FragmentCreateDto dto);
    default void update(FragmentEntity fragment, FragmentUpdateDto dto) {
        fragment.setDescription(dto.getDescription());
    }
}
