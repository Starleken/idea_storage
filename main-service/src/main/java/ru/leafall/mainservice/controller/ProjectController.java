package ru.leafall.mainservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import ru.leafall.mainservice.dto.project.ProjectCreateDto;
import ru.leafall.mainservice.dto.project.ProjectFullDto;
import ru.leafall.mainservice.dto.project.ProjectUpdateDto;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.leafall.mainservice.service.ProjectService;
import ru.leafall.mainservice.utils.LogsUtils;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;

import java.util.*;

import static java.util.stream.Collectors.*;

@RestController
@RequestMapping("/v1/projects")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "projects", description = "все ваши проекты")
public class ProjectController {

    private final ProjectService service;

    @GetMapping
    @Operation(summary = "поиск всех проектов",
            description = "ищите все проекты, которые предлагают пользователи")
    public ResponseEntity<List<ProjectFullDto>> findAllByIds(@RequestParam(name = PaginationParams.LIMIT, defaultValue = "10") @Min(0) Integer limit,
                                                            @RequestParam(name = PaginationParams.PAGE, defaultValue = "0") @Min(0) Integer page,
                                                            @RequestParam(name = "ids", required = false) Set<Long> ids
    ) {
        PaginationResponse<ProjectFullDto> result = null;
        var params = new PaginationParams(limit, page);
        var identityKeys = ids == null ? "" : ids.stream().map(Objects::toString)
                                .collect(joining("&id=", "id=", ""));
        log.info(LogsUtils.getLogs(ProjectController.class, "findAllByIds" + identityKeys, params));
        if(ids == null || ids.isEmpty()) {
            result = service.findAll(params);
        } else {
            result = service.findAllByIds(ids, params);
        }
        log.info(LogsUtils.getResponseLogs(ProjectController.class, "findAllByIds" + identityKeys, result.totalCount()));
        return ResponseEntity.ok()
                .header(PaginationParams.HEADER_TOTAL_COUNT, result.totalCount().toString())
                .body(result.items());
    }

    @GetMapping("{id}")
    @Operation(summary = "поиск определенного проекта",
            description = "ищите определенный проект по id")
    public ResponseEntity<ProjectFullDto> findById(@PathVariable @Min(0) Long id) {
        log.info(LogsUtils.getLogs(ProjectController.class, "findById", id));
        var item = service.findById(id);
        log.info(LogsUtils.getResponseLogs(ProjectController.class, "findById", item));
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "создайте свой проект",
            description = "позволяет создать ваш проект и записать туда идею")
    public ResponseEntity<ProjectFullDto> create(@RequestBody @Valid ProjectCreateDto dto) {
        log.info(LogsUtils.getLogs(ProjectController.class, "create", dto));
        var item = service.create(dto);
        log.info(LogsUtils.getResponseLogs(ProjectController.class, "create", item));
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "обновите свой проект",
            description = "позволяет обновить ваш проект.")
    public ResponseEntity<ProjectFullDto> update(@RequestBody @Valid ProjectUpdateDto dto) {
        log.info(LogsUtils.getLogs(ProjectController.class, "update", dto));
        var item = service.update(dto);
        log.info(LogsUtils.getLogs(ProjectController.class, "update", item));
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "удалите свой проект",
            description = "позволяет удалить проект, если идея больше не является привлекательной навсегда!")
    public ResponseEntity<Long> delete(@PathVariable @Min(0) Long id) {
        log.info(LogsUtils.getLogs(ProjectController.class, "delete", id));
        var item = service.delete(id);
        log.info(LogsUtils.getLogs(ProjectController.class, "delete", item));
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
