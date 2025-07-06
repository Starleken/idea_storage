package ru.leafall.mainservice.service;

import ru.leafall.mainservice.dto.hotkey.HotkeyCreateDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyFullDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyShortDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyUpdateDto;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;

import java.util.List;

public interface HotkeyService {
    PaginationResponse<HotkeyShortDto> findAllByProjectId(Long projectId, PaginationParams params);
    HotkeyFullDto findById(Long id);
    HotkeyShortDto create(HotkeyCreateDto dto);
    List<HotkeyShortDto> create(List<HotkeyCreateDto> dto);
    HotkeyShortDto update(HotkeyUpdateDto dto);
    Long delete(Long id);
}
