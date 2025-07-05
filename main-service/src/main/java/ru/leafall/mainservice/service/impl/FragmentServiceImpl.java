package ru.leafall.mainservice.service.impl;

import ru.leafall.mainservice.dto.fragment.FragmentCreateDto;
import ru.leafall.mainservice.dto.fragment.FragmentFullDto;
import ru.leafall.mainservice.dto.fragment.FragmentShortDto;
import ru.leafall.mainservice.dto.fragment.FragmentUpdateDto;
import ru.leafall.mainservice.entity.FragmentEntity;
import ru.leafall.mainservice.mapper.FragmentMapper;
import ru.leafall.mainservice.repository.FragmentRepository;
import ru.leafall.mainservice.service.FragmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leafall.mainstarter.utils.ThrowableUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FragmentServiceImpl implements FragmentService {

    private final FragmentMapper mapper;
    private final FragmentRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<FragmentShortDto> findAllByProjectId(Long projectId) {
        List<FragmentEntity> fragments = repository.findAllByProjectId(projectId);
        return fragments.stream().map(mapper::mapToShortDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public FragmentFullDto findById(Long id) {
        FragmentEntity fragment = findByIdOrThrowNotFoundException(id);

        return mapper.mapToFullDto(fragment);
    }

    @Override
    @Transactional
    public FragmentShortDto create(FragmentCreateDto dto) {
        FragmentEntity fragment = mapper.mapToEntity(dto);
        FragmentEntity savedFragment = repository.save(fragment);
        return mapper.mapToShortDto(savedFragment);
    }

    @Override
    @Transactional
    public FragmentShortDto update(FragmentUpdateDto dto) {
        FragmentEntity fragment = findByIdOrThrowNotFoundException(dto.getId());
        mapper.update(fragment, dto);
        FragmentEntity savedFragment = repository.save(fragment);
        return mapper.mapToShortDto(savedFragment);
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        FragmentEntity fragment = findByIdOrThrowNotFoundException(id);
        repository.delete(fragment);
        return id;
    }

    private FragmentEntity findByIdOrThrowNotFoundException(Long id) {
        return repository.findById(id).orElseThrow(()->
                ThrowableUtils.getNotFoundException("Fragment with id %d is not found", id)
        );
    }
}
