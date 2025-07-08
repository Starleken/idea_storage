package ru.leafall.mainservice.service;

import org.springframework.data.domain.Page;
import ru.leafall.mainservice.dto.technology.TechnologyCreateDto;
import ru.leafall.mainservice.dto.technology.TechnologyFullDto;
import ru.leafall.mainservice.dto.technology.TechnologyUpdateDto;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;

import java.util.List;

public interface TechnologyService {
    PaginationResponse<TechnologyFullDto> findAll(PaginationParams params);
    TechnologyFullDto findById(Integer id);
    TechnologyFullDto create(TechnologyCreateDto dto);
    TechnologyFullDto update(TechnologyUpdateDto dto);
    Integer delete(Integer id);
}
