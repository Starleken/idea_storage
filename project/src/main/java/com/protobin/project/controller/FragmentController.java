package com.protobin.project.controller;

import com.protobin.project.dto.fragment.FragmentCreateDto;
import com.protobin.project.dto.fragment.FragmentResponseDto;
import com.protobin.project.dto.fragment.FragmentUpdateDto;
import com.protobin.project.dto.fragment.FragmentUpdateImageDto;
import com.protobin.project.exception.annotation.ApiResponseBadRequest;
import com.protobin.project.exception.annotation.ApiResponseForbidden;
import com.protobin.project.exception.annotation.ApiResponseNoAuth;
import com.protobin.project.exception.annotation.ApiResponseNotFound;
import com.protobin.project.service.FragmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "v1/fragments")
@Tag(name = "Fragments", description = "Необходимые руты для работы с фрагментами")
@RequiredArgsConstructor
@Slf4j
public class FragmentController {

    private final FragmentService fragmentService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить фрагмент по UUID",
            description = "Получить фрагмент по id",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Был получен фрагмент")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @ApiResponseNoAuth
    @ApiResponseForbidden
    public ResponseEntity<FragmentResponseDto> findById(@PathVariable UUID id) {
        var fragment = fragmentService.findById(id);
        return new ResponseEntity<>(fragment, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(
            summary = "Создать фрагмент",
            description = "Был создан новый фрагмент для проекта",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "201", description = "Создан новый фрагмент")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @ApiResponseNoAuth
    @ApiResponseForbidden
    public ResponseEntity<FragmentResponseDto> create(@ModelAttribute FragmentCreateDto dto) {
        var fragment = fragmentService.create(dto);
        return new ResponseEntity<>(fragment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновить фрагмент",
            description = "Был обновлен фрагмент",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Обновлен определенный фрагмент")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @ApiResponseNoAuth
    @ApiResponseForbidden
    public ResponseEntity<FragmentResponseDto> update(@PathVariable UUID id, @RequestBody FragmentUpdateDto dto) {
        var fragment = fragmentService.update(id, dto);
        return new ResponseEntity<>(fragment, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(
            summary = "Обновить фрагмент фото",
            description = "Был обновлен фрагмент фото",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Обновлен определенный фрагмент фото")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @ApiResponseNoAuth
    @ApiResponseForbidden
    public ResponseEntity<FragmentResponseDto> updateImage(@PathVariable UUID id, @ModelAttribute FragmentUpdateImageDto dto) {
        var fragment = fragmentService.updateImage(id, dto);
        return new ResponseEntity<>(fragment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить фрагмент",
            description = "Удаляет фрагмент без возможности восстановления",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "204", description = "Удален фрагмент")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @ApiResponseNoAuth
    @ApiResponseForbidden
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        fragmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}/image")
    @Operation(
            summary = "Удалить фрагмент фото",
            description = "Удаляет фото у фрагмента без возможности восстановления",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Удалено фото у фрагмента")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @ApiResponseNoAuth
    @ApiResponseForbidden
    public ResponseEntity<FragmentResponseDto> deleteImage(@PathVariable UUID id) {
        var fragment = fragmentService.deleteImageById(id);
        return new ResponseEntity<>(fragment, HttpStatus.OK);
    }
}
