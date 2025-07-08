package ru.leafall.mainservice.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;
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
    public PaginationResponse<TechnologyFullDto> findAll(PaginationParams params) {
        Sort sort = Sort.by("name").ascending();
        var pageable = PageRequest.of(params.page(), params.limit(), sort);
        var technologies = repository.findAll(pageable);
        var result = technologies.map(mapper::mapToFullDto);
        return new PaginationResponse<>(result.getContent(), result.getTotalElements());
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
        technology.setName(technology.getName().toLowerCase().trim());
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
