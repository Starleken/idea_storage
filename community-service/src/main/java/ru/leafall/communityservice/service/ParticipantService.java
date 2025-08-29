package ru.leafall.communityservice.service;

import ru.leafall.communityservice.dto.participant.ParticipantCreateDto;
import ru.leafall.communityservice.dto.participant.ParticipantResponseFullDto;
import ru.leafall.communityservice.dto.participant.ParticipantUpdateDto;
import ru.leafall.mainstarter.model.Project;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;

public interface ParticipantService {
    PaginationResponse<Project> findAllByUserId(Long userId, PaginationParams params);
    ParticipantResponseFullDto findById(Long id);
    ParticipantResponseFullDto create(ParticipantCreateDto dto);
    ParticipantResponseFullDto update(ParticipantUpdateDto dto);
    ParticipantResponseFullDto delete(Long id);
}
