package ru.leafall.mainservice.service;

import ru.leafall.mainservice.dto.project.related.RelatedProjectCreateDto;
import ru.leafall.mainservice.dto.project.related.RelatedProjectFullDto;
import ru.leafall.mainservice.dto.project.related.RelatedProjectShortDto;
import ru.leafall.mainservice.dto.project.related.RelatedProjectUpdateDto;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;

public interface RelatedProjectService {

    PaginationResponse<RelatedProjectShortDto> findAllByProjectId(Long projectId, PaginationParams params);
    RelatedProjectFullDto findById(Long id);
    RelatedProjectShortDto create(RelatedProjectCreateDto dto);
    RelatedProjectShortDto update(RelatedProjectUpdateDto dto);
    Long delete(Long id);

}
