package ru.leafall.mainservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.leafall.fileservicestarter.dto.FileResponseDto;
import ru.leafall.fileservicestarter.service.FileService;
import ru.leafall.mainservice.dto.fragment.FragmentCreateDto;
import ru.leafall.mainservice.dto.fragment.FragmentFullDto;
import ru.leafall.mainservice.dto.fragment.FragmentShortDto;
import ru.leafall.mainservice.dto.fragment.FragmentUpdateDto;
import ru.leafall.mainservice.entity.FragmentEntity;
import ru.leafall.mainservice.entity.ProjectEntity;
import ru.leafall.mainservice.mapper.FragmentMapper;
import ru.leafall.mainservice.repository.FragmentRepository;
import ru.leafall.mainservice.repository.ProjectRepository;
import ru.leafall.mainservice.service.FragmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;
import ru.leafall.mainstarter.utils.ThrowableUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class FragmentServiceImpl implements FragmentService {

    private final FragmentMapper mapper;
    private final FragmentRepository repository;
    private final ProjectRepository projectRepository;
    private final FileService fileService;

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse<FragmentShortDto> findAllByProjectId(Long projectId, PaginationParams params) {
        Sort sort = Sort.by("id").ascending();
        var pageable = PageRequest.of(params.page(), params.limit(), sort);
        var fragments = repository.findAllByProjectId(projectId, pageable);
        var result = fragments.map(mapper::mapToShortDto);
        return new PaginationResponse<>(result.getContent(), fragments.getTotalElements());
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
        var project = findProjectOrThrowNotFoundException(dto.getProjectId());
        var fragment = mapper.mapToEntity(dto);
        var picture = sendMessageToFileService(dto);

        fragment.setPicture(picture.getId().toString());
        fragment.setProject(project);
        var savedFragment = repository.save(fragment);
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

    private ProjectEntity findProjectOrThrowNotFoundException(Long id) {
        return projectRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.getNotFoundException("Project with id is not found", id)
        );
    }

    private FileResponseDto sendMessageToFileService(FragmentCreateDto dto) {
        int iteration = 0;
        final int maxCountIterations = 3;
        FileResponseDto picture = new FileResponseDto();
        while (picture.getId() == null) {
            try {
                picture = fileService.upload(dto.getPicture());
            } catch (Exception exception){
                if (++iteration > maxCountIterations) {
                    throw new RuntimeException(exception);
                }
            }
        }
        return picture;
    }
}
