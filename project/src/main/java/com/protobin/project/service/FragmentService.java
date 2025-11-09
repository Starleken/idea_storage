package com.protobin.project.service;

import com.protobin.project.dto.fragment.FragmentCreateDto;
import com.protobin.project.dto.fragment.FragmentResponseDto;
import com.protobin.project.dto.fragment.FragmentUpdateDto;
import com.protobin.project.dto.fragment.FragmentUpdateImageDto;
import com.protobin.project.helper.PaginationParams;
import com.protobin.project.helper.PaginationResponse;

import java.util.UUID;

public interface FragmentService {
    PaginationResponse<FragmentResponseDto> findByProjectId(UUID projectId, PaginationParams pagination);
    FragmentResponseDto findById(UUID fragmentId);
    FragmentResponseDto create(FragmentCreateDto dto);
    FragmentResponseDto update(UUID id, FragmentUpdateDto dto);
    FragmentResponseDto updateImage(UUID id, FragmentUpdateImageDto dto);
    void deleteById(UUID fragmentId);
    FragmentResponseDto deleteImageById(UUID fragmentId);
}
