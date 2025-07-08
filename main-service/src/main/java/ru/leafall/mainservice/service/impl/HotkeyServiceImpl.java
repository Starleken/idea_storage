package ru.leafall.mainservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leafall.mainservice.dto.hotkey.HotkeyCreateDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyFullDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyShortDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyUpdateDto;
import ru.leafall.mainservice.entity.HotkeyEntity;
import ru.leafall.mainservice.entity.ProjectEntity;
import ru.leafall.mainservice.mapper.HotkeyMapper;
import ru.leafall.mainservice.repository.HotkeyRepository;
import ru.leafall.mainservice.repository.ProjectRepository;
import ru.leafall.mainservice.service.HotkeyService;
import ru.leafall.mainservice.service.ProjectService;
import ru.leafall.mainstarter.exception.BadRequestException;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;
import ru.leafall.mainstarter.utils.ThrowableUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotkeyServiceImpl implements HotkeyService {

    private final HotkeyRepository repository;
    private final ProjectRepository projectRepository;
    private final HotkeyMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse<HotkeyShortDto> findAllByProjectId(Long projectId, PaginationParams params) {
        var sort = Sort.by("id").ascending();
        var pageable = PageRequest.of(params.page(), params.limit(), sort);
        var hotkeys = repository.findAllByProjectId(projectId, pageable);
        var result = hotkeys.map(mapper::mapToShortDto);
        return new PaginationResponse<>(result.getContent(), result.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public HotkeyFullDto findById(Long id) {
        var result = findHotkeyOrThrowNotFoundException(id);
        return mapper.mapToFullDto(result);
    }

    @Override
    @Transactional
    public HotkeyShortDto create(HotkeyCreateDto dto) {
        var project = findProjectOrThrowNotFoundException(dto.getProjectId());
        var hotkey = mapper.mapToEntity(dto);
        hotkey.setProject(project);
        var result = repository.save(hotkey);
        return mapper.mapToShortDto(result);
    }

    @Override
    @Transactional
    public List<HotkeyShortDto> create(List<HotkeyCreateDto> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            throw new BadRequestException("List hotkeys is empty or equals nullable");
        }
        final var firstItemIndex = 0;
        final var projectId = dtos.get(firstItemIndex).getProjectId();
        final var project = findProjectOrThrowNotFoundException(projectId);

        var list = new LinkedList<HotkeyEntity>();

        for (var dto: dtos) {
            var item = mapper.mapToEntity(dto);
            item.setProject(project);
            list.add(item);
        }
        var savedHotkeys = repository.saveAll(list);
        return savedHotkeys.stream().map(mapper::mapToShortDto).toList();
    }

    @Override
    @Transactional
    public HotkeyShortDto update(HotkeyUpdateDto dto) {
        var hotkey = findHotkeyOrThrowNotFoundException(dto.getId());
        mapper.update(hotkey, dto);
        var savedHotkey = repository.save(hotkey);
        return mapper.mapToShortDto(savedHotkey);
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        var hotkey = findHotkeyOrThrowNotFoundException(id);
        repository.delete(hotkey);
        return hotkey.getId();
    }

    private HotkeyEntity findHotkeyOrThrowNotFoundException(Long id) {
        return repository.findById(id).orElseThrow(() ->
                ThrowableUtils.getNotFoundException("Hotkey with id %d is not found", id)
        );
    }

    private ProjectEntity findProjectOrThrowNotFoundException(Long id) {
        return projectRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.getNotFoundException("Project with id is not found", id)
        );
    }
}
