package ru.leafall.mainservice.service;

import ru.leafall.mainservice.dto.hotkey.HotkeyCreateDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyFullDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyShortDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyUpdateDto;

import java.util.List;

public interface HotkeyService {
    List<HotkeyShortDto> findAllByProjectId(Long projectId);
    HotkeyFullDto findById(Long id);
    HotkeyShortDto create(HotkeyCreateDto dto);
    List<HotkeyShortDto> create(List<HotkeyCreateDto> dto);
    HotkeyShortDto update(HotkeyUpdateDto dto);
    HotkeyUpdateDto delete(Long id);
}
