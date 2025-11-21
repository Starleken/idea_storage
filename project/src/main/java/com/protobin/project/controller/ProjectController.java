package com.protobin.project.controller;

import com.protobin.project.dto.fragment.FragmentResponseDto;
import com.protobin.project.dto.project.ProjectCreateDto;
import com.protobin.project.dto.project.ProjectResponseDto;
import com.protobin.project.dto.project.ProjectUpdateDto;
import com.protobin.project.exception.annotation.ApiResponseBadRequest;
import com.protobin.project.exception.annotation.ApiResponseForbidden;
import com.protobin.project.exception.annotation.ApiResponseNoAuth;
import com.protobin.project.exception.annotation.ApiResponseNotFound;
import com.protobin.project.helper.PaginationConstants;
import com.protobin.project.helper.PaginationParams;
import com.protobin.project.service.FragmentService;
import com.protobin.project.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
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
@RequestMapping(path = "v1/projects")
@Tag(name = "Projects", description = "Необходимые руты для работы с проектами")
public class ProjectController {

    private final ProjectService projectService;
    private final FragmentService fragmentService;

    @GetMapping("/{projectId}/fragments")
    @Tag(name = "Fragments")
    @Operation(
            summary = "Получить фрагменты по UUID",
            description = "Получить все фрагменты с **пагинацией**",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Были получены фрагменты" )
    @ApiResponseNoAuth
    @ApiResponseForbidden
    @ApiResponseNotFound
    public ResponseEntity<List<FragmentResponseDto>> findAllFragmentsByProjectId(
            @PathVariable UUID projectId,
            @RequestParam(name = PaginationConstants.LIMIT, defaultValue = "10") @Min(0) Integer limit,
            @RequestParam(name = PaginationConstants.PAGE, defaultValue = "0") @Min(0) Integer page) {
        var pagination = new PaginationParams(limit, page);
        var found = fragmentService.findByProjectId(projectId, pagination);

        return ResponseEntity.ok()
                .header(PaginationConstants.HEADER_TOTAL_COUNT, found.total().toString())
                .header(PaginationConstants.HEADER_LIMIT, pagination.limit().toString())
                .header(PaginationConstants.HEADER_PAGE, pagination.page().toString())
                .body(found.items());
    }

    @GetMapping("/tags")
    @Operation(
            summary = "Получить проекты по тэгам",
            description = "Получить все проекты, включающие указанные тэги",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Были получены проекты" )
    @ApiResponseNoAuth
    @ApiResponseForbidden
    @ApiResponseNotFound
    public ResponseEntity<List<ProjectResponseDto>> findAllByTags(
            @RequestBody List<String> tagsName,
            @RequestParam(name = PaginationConstants.LIMIT, defaultValue = "10") @Min(0) Integer limit,
            @RequestParam(name = PaginationConstants.PAGE, defaultValue = "0") @Min(0) Integer page) {
        var pagination = new PaginationParams(limit, page);
        var found = projectService.findAllByTags(tagsName, pagination);

        return ResponseEntity.ok()
                .header(PaginationConstants.HEADER_TOTAL_COUNT, found.total().toString())
                .header(PaginationConstants.HEADER_LIMIT, pagination.limit().toString())
                .header(PaginationConstants.HEADER_PAGE, pagination.page().toString())
                .body(found.items());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить проект по UUID",
            description = "Получить проект по его идентификатору",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Был получен проект" )
    @ApiResponseForbidden
    @ApiResponseNotFound
    public ResponseEntity<ProjectResponseDto> findById(@PathVariable UUID id) {
        var found = projectService.findById(id);

        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @PostMapping
    @Operation(
            summary = "Создать проект",
            description = "Создается новый проект для текущего пользователя",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "201", description = "Был создан проект" )
    @ApiResponseBadRequest
    @ApiResponseNoAuth
    public ResponseEntity<ProjectResponseDto> create(@RequestBody ProjectCreateDto createDto) {
        var created = projectService.create(createDto);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(
            summary = "Обновить данные о проекте",
            description = "Обновляются данные о выбранном проекте",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Был обновлен проект" )
    @ApiResponseBadRequest
    @ApiResponseNoAuth
    @ApiResponseForbidden
    @ApiResponseNotFound
    public ResponseEntity<ProjectResponseDto> update(@RequestBody ProjectUpdateDto updateDto) {
        var updated = projectService.update(updateDto);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить проект",
            description = "Удаляется проект через soft, то есть можно восстановить",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "204", description = "Был удален проект")
    @ApiResponseNoAuth
    @ApiResponseForbidden
    @ApiResponseNotFound
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        projectService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
