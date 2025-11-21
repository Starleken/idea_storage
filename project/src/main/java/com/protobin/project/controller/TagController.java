package com.protobin.project.controller;

import com.protobin.project.dto.project.ProjectCreateDto;
import com.protobin.project.dto.project.ProjectResponseDto;
import com.protobin.project.dto.tag.TagCreateAllDto;
import com.protobin.project.dto.tag.TagResponseDto;
import com.protobin.project.exception.annotation.ApiResponseBadRequest;
import com.protobin.project.exception.annotation.ApiResponseForbidden;
import com.protobin.project.exception.annotation.ApiResponseNoAuth;
import com.protobin.project.exception.annotation.ApiResponseNotFound;
import com.protobin.project.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "v1/tags")
@Tag(name = "Tags", description = "Необходимые руты для работы с тэгами")
public class TagController {

    private final TagService tagService;

    @PostMapping
    @Operation(
            summary = "Создать список тэгов",
            description = "Создается список тэгов для указанного проекта",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "201", description = "Тэги созданы" )
    @ApiResponseNotFound
    @ApiResponseBadRequest
    @ApiResponseForbidden
    @ApiResponseNoAuth
    public ResponseEntity<List<TagResponseDto>> create(@RequestBody TagCreateAllDto createAllDto) {
        var created = tagService.createAll(createAllDto);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить тэг",
            description = "Удаляется тэг",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "204", description = "Был удален тэг")
    @ApiResponseNoAuth
    @ApiResponseForbidden
    @ApiResponseNotFound
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        tagService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
