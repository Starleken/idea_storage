package ru.leafall.mainservice.service.impl;

import ru.leafall.mainservice.dto.technology.TechnologyCreateDto;
import ru.leafall.mainservice.dto.technology.TechnologyFullDto;
import ru.leafall.mainservice.dto.technology.TechnologyUpdateDto;
import ru.leafall.mainservice.entity.TechnologyEntity;
import ru.leafall.mainservice.mapper.TechnologyMapper;
import ru.leafall.mainservice.repository.TechnologyRepository;
import ru.leafall.mainservice.service.TechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leafall.mainstarter.utils.ThrowableUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyRepository repository;
    private final TechnologyMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<TechnologyFullDto> findAll() {
        List<TechnologyEntity> technologies = repository.findAll();
        return technologies.stream()
                .map(mapper::mapToFullDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TechnologyFullDto findById(Integer id) {
        TechnologyEntity technology = findByIdOrElseThrowNotFoundException(id);
        return mapper.mapToFullDto(technology);
    }

    @Override
    @Transactional
    public TechnologyFullDto create(TechnologyCreateDto dto) {
        TechnologyEntity technology = mapper.mapToEntity(dto);
        TechnologyEntity saved = repository.save(technology);
        return mapper.mapToFullDto(saved);
    }

    @Override
    @Transactional
    public TechnologyFullDto update(TechnologyUpdateDto dto) {
        TechnologyEntity technology = findByIdOrElseThrowNotFoundException(dto.getId());
        mapper.update(technology, dto);
        TechnologyEntity saved = repository.save(technology);
        return mapper.mapToFullDto(saved);
    }

    @Override
    @Transactional
    public Integer delete(Integer id) {
        TechnologyEntity technology = findByIdOrElseThrowNotFoundException(id);
        repository.delete(technology);
        return id;
    }

    private TechnologyEntity findByIdOrElseThrowNotFoundException(Integer id) {
        return repository.findById(id).orElseThrow(() -> ThrowableUtils.getNotFoundException (
                "Technology with id=%d is not found, please check your id", id
        ));
    }
}
