package com.protobin.project.service.impl;

import com.protobin.project.dto.boardElement.BoardElementCreateDto;
import com.protobin.project.dto.boardElement.BoardElementResponseDto;
import com.protobin.project.dto.boardElement.BoardElementUpdateDto;
import com.protobin.project.entity.BoardElementEntity;
import com.protobin.project.entity.FragmentEntity;
import com.protobin.project.entity.ProjectEntity;
import com.protobin.project.exception.NotFoundException;
import com.protobin.project.mapper.BoardElementMapper;
import com.protobin.project.repository.BoardElementRepository;
import com.protobin.project.repository.ProjectRepository;
import com.protobin.project.service.BoardElementService;
import liquibase.util.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardElementServiceImpl implements BoardElementService {

    private final BoardElementRepository boardElementRepository;
    private final ProjectRepository projectRepository;

    private final BoardElementMapper mapper;

    @Override
    public List<BoardElementResponseDto> findAllByProject(UUID projectId) {
        var foundProject = findProjectById(projectId);

        return boardElementRepository.findAllByProject(foundProject)
                .stream()
                .map(mapper::mapToDto)
                .toList();
    }

    @Override
    public BoardElementResponseDto create(BoardElementCreateDto createDto) {
        var foundProject = findProjectById(createDto.getProjectId());

        var toSave = mapper.mapToEntity(createDto);
        toSave.setProject(foundProject);
        var saved = boardElementRepository.save(toSave);

        return mapper.mapToDto(saved);
    }

    @Override
    public BoardElementResponseDto update(BoardElementUpdateDto updateDto) {
        var found = findBoardElementById(updateDto.getId());
        mapper.update(found, updateDto);

        var updated = boardElementRepository.save(found);

        return mapper.mapToDto(updated);
    }

    @Override
    public void deleteById(UUID id) {
        var found = findBoardElementById(id);
        boardElementRepository.delete(found);
    }

    @Override
    public void deleteByIds(List<UUID> ids) {
        boardElementRepository.deleteAllById(ids);
    }

    private BoardElementEntity findBoardElementById(UUID boardElementId) {
        return boardElementRepository.findById(boardElementId)
                .orElseThrow(() -> new NotFoundException("board element is not found"));
    }

    private ProjectEntity findProjectById(UUID projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("Project is not found"));
    }
}
