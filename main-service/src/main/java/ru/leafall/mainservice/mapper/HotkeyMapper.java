package ru.leafall.mainservice.mapper;

import ru.leafall.mainservice.dto.hotkey.HotkeyCreateDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyFullDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyShortDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyUpdateDto;
import ru.leafall.mainservice.entity.HotkeyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProjectMapper.class})
public interface HotkeyMapper {
    HotkeyFullDto mapToFullDto(HotkeyEntity hotkey);
    HotkeyShortDto mapToShortDto(HotkeyEntity hotkey);
    HotkeyEntity mapToEntity(HotkeyCreateDto dto);

    default void update(HotkeyEntity entity, HotkeyUpdateDto dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
    }
}
