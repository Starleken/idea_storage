package com.protobin.project.service;

import com.protobin.project.dto.boardElement.BoardElementCreateDto;
import com.protobin.project.dto.boardElement.BoardElementResponseDto;
import com.protobin.project.dto.boardElement.BoardElementUpdateDto;

import java.util.List;
import java.util.UUID;

public interface BoardElementService {

    List<BoardElementResponseDto> findAllByProject(UUID projectId);
    BoardElementResponseDto create(BoardElementCreateDto createDto);
    BoardElementResponseDto update(BoardElementUpdateDto updateDto);
    void deleteById(UUID id);
    void deleteByIds(List<UUID> ids);
}
