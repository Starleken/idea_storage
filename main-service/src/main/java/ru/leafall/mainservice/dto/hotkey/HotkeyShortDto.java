package ru.leafall.mainservice.dto.hotkey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotkeyShortDto {
    private Long id;

    private String name;

    private String description;

    private Long projectId;
}
