package com.protobin.project.service.impl;

import com.protobin.project.dto.fragment.FragmentCreateDto;
import com.protobin.project.dto.fragment.FragmentResponseDto;
import com.protobin.project.dto.fragment.FragmentUpdateDto;
import com.protobin.project.dto.fragment.FragmentUpdateImageDto;
import com.protobin.project.entity.FragmentEntity;
import com.protobin.project.entity.ProjectEntity;
import com.protobin.project.exception.BadRequestException;
import com.protobin.project.exception.NotFoundException;
import com.protobin.project.helper.PaginationParams;
import com.protobin.project.helper.PaginationResponse;
import com.protobin.project.mapper.FragmentMapper;
import com.protobin.project.repository.FragmentRepository;
import com.protobin.project.repository.ProjectRepository;
import com.protobin.project.service.FileService;
import com.protobin.project.service.FragmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FragmentServiceImpl implements FragmentService {

    private final FragmentRepository fragmentRepository;
    private final ProjectRepository projectRepository;
    private final FragmentMapper fragmentMapper;
    private final FileService fileService;

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse<FragmentResponseDto> findByProjectId(UUID projectId, PaginationParams pagination) {
        var sort = Sort.by("id").ascending();
        var pageable = PageRequest.of(pagination.page(), pagination.limit(), sort);

        var project = findByIdProjectOrThrowNotFound(projectId);
        var fragmentPage = fragmentRepository.findAllByProject(project, pageable);

        var mappedResponses = fragmentPage.toList().stream().map(fragmentMapper::mapToDto).toList();
        return new PaginationResponse<>(mappedResponses, fragmentPage.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public FragmentResponseDto findById(UUID fragmentId) {
        var fragment = findByIdFragmentOrThrowNotFound(fragmentId);
        return fragmentMapper.mapToDto(fragment);
    }

    @Override
    @Transactional
    public FragmentResponseDto create(FragmentCreateDto dto) {
        var image = dto.getImage();
        var project = findByIdProjectOrThrowNotFound(dto.getProjectId());
        var fragment = fragmentMapper.mapToEntity(dto);
        if (!image.isEmpty()) {
            var imageName = fileService.uploadImage(image);
            fragment.setImage(imageName);
        }
        fragment.setProject(project);
        var createdFragment = fragmentRepository.save(fragment);
        return fragmentMapper.mapToDto(createdFragment);
    }

    @Override
    @Transactional
    public FragmentResponseDto update(UUID id, FragmentUpdateDto dto) {
        var fragment = findByIdFragmentOrThrowNotFound(id);
        fragmentMapper.mapToEntity(fragment, dto);
        var updatedFragment = fragmentRepository.save(fragment);
        return fragmentMapper.mapToDto(updatedFragment);
    }

    @Override
    @Transactional
    public FragmentResponseDto updateImage(UUID id, FragmentUpdateImageDto dto) {
        var fragment = findByIdFragmentOrThrowNotFound(id);
        if(fragment.getImage() != null && !fragment.getImage().isEmpty()) {
            fileService.removeImage(fragment.getImage());
        }
        fragment.setImage(fileService.uploadImage(dto.getImage()));
        var updatedFragment = fragmentRepository.save(fragment);
        return fragmentMapper.mapToDto(updatedFragment);
    }

    @Override
    @Transactional
    public void deleteById(UUID fragmentId) {
        var fragment = findByIdFragmentOrThrowNotFound(fragmentId);
        fragmentRepository.delete(fragment);
    }

    @Override
    @Transactional
    public FragmentResponseDto deleteImageById(UUID fragmentId) {
        var fragment = findByIdFragmentOrThrowNotFound(fragmentId);
        if(fragment.getImage() != null && !fragment.getImage().isEmpty()) {
            fileService.removeImage(fragment.getImage());
        }
        fragment.setImage(null);
        var updatedFragment = fragmentRepository.save(fragment);
        return fragmentMapper.mapToDto(updatedFragment);
    }


    private ProjectEntity findByIdProjectOrThrowNotFound(UUID projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("Project is not found"));
    }

    private FragmentEntity findByIdFragmentOrThrowNotFound(UUID fragmentId) {
        return fragmentRepository.findById(fragmentId)
                .orElseThrow(() -> new NotFoundException("Project is not found"));
    }


}
