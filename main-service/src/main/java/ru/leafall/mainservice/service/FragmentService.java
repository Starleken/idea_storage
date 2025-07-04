package ru.leafall.mainservice.service;

import ru.leafall.mainservice.dto.fragment.FragmentCreateDto;
import ru.leafall.mainservice.dto.fragment.FragmentFullDto;
import ru.leafall.mainservice.dto.fragment.FragmentShortDto;
import ru.leafall.mainservice.dto.fragment.FragmentUpdateDto;

import java.util.List;

public interface FragmentService {
    List<FragmentShortDto> findAllByProjectId(Long projectId);
    FragmentFullDto findById(Long id);
    FragmentShortDto create(FragmentCreateDto dto);
    FragmentShortDto update(FragmentUpdateDto dto);
    Long delete(Long id);
}
