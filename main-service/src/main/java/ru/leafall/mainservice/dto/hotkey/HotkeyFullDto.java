package ru.leafall.mainservice.dto.hotkey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import ru.leafall.mainservice.dto.project.ProjectFullDto;
import ru.leafall.mainservice.entity.ProjectEntity;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotkeyFullDto {

    private Long id;

    private String name;

    private String description;

    private ProjectFullDto project;
}
