package ru.leafall.mainservice.service;

import org.springframework.data.domain.Page;
import ru.leafall.mainservice.dto.fragment.FragmentCreateDto;
import ru.leafall.mainservice.dto.fragment.FragmentFullDto;
import ru.leafall.mainservice.dto.fragment.FragmentShortDto;
import ru.leafall.mainservice.dto.fragment.FragmentUpdateDto;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;

import java.util.List;

public interface FragmentService {
    PaginationResponse<FragmentShortDto> findAllByProjectId(Long projectId, PaginationParams params);
    FragmentFullDto findById(Long id);
    FragmentShortDto create(FragmentCreateDto dto);
    FragmentShortDto update(FragmentUpdateDto dto);
    Long delete(Long id);
}
