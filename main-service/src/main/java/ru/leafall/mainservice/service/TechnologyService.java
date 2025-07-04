package ru.leafall.mainservice.service;

import ru.leafall.mainservice.dto.technology.TechnologyCreateDto;
import ru.leafall.mainservice.dto.technology.TechnologyFullDto;
import ru.leafall.mainservice.dto.technology.TechnologyUpdateDto;

import java.util.List;

public interface TechnologyService {
    List<TechnologyFullDto> findAll();
    TechnologyFullDto findById(Integer id);
    TechnologyFullDto create(TechnologyCreateDto dto);
    TechnologyFullDto update(TechnologyUpdateDto dto);
    Integer delete(Integer id);
}
