package ru.leafall.communityservice.service;

import ru.leafall.communityservice.dto.step.StepCreateDto;
import ru.leafall.communityservice.dto.step.StepFullDto;
import ru.leafall.communityservice.dto.step.StepUpdateDto;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;

public interface StepService {
    PaginationResponse<StepFullDto> findAllByTaskId(Long taskId, PaginationParams params);
    StepFullDto findById(Long id);
    StepFullDto create(StepCreateDto dto);
    StepFullDto update(StepUpdateDto dto);
    StepFullDto delete(Long id);
}
