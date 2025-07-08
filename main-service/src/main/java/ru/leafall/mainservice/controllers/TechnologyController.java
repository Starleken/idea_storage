package ru.leafall.mainservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import ru.leafall.mainservice.dto.technology.TechnologyCreateDto;
import ru.leafall.mainservice.dto.technology.TechnologyFullDto;
import ru.leafall.mainservice.dto.technology.TechnologyUpdateDto;
import ru.leafall.mainservice.service.TechnologyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leafall.mainservice.utils.LogsUtils;
import ru.leafall.mainstarter.utils.PaginationParams;

import java.util.List;

@RestController
@RequestMapping("/v1/technologies")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "technologies", description = "здесь хранятся все технологии для реализации ваших проектов")
public class TechnologyController {

    private final TechnologyService service;

    @GetMapping
    @Operation(summary = "все технологии",
            description = "получите все технологии, которые могут быть применены для реализации")
    public ResponseEntity<List<TechnologyFullDto>> findAll(
            @RequestParam(name = PaginationParams.LIMIT, defaultValue = "10") @Min(0) Integer limit,
            @RequestParam(name = PaginationParams.PAGE, defaultValue = "0") @Min(0) Integer page
    ) {
        var params = new PaginationParams(limit, page);
        log.info(LogsUtils.getLogs(TechnologyController.class, "findAll", params));
        var result = service.findAll(params);
        log.info(LogsUtils.getResponseLogs(TechnologyController.class, "findAll", result.totalCount()));
        return ResponseEntity.ok()
                .header(PaginationParams.HEADER_TOTAL_COUNT, result.totalCount().toString())
                .body(result.items());
    }

    @GetMapping("{id}")
    @Operation(summary = "определенная технология",
            description = "получите технологию, которая может быть применена для реализации")
    public ResponseEntity<TechnologyFullDto> findById(@PathVariable Integer id) {
        log.info(LogsUtils.getLogs(TechnologyController.class, "findById", id));
        var dto  = service.findById(id);
        log.info(LogsUtils.getResponseLogs(TechnologyController.class, "findById", dto));
        return ResponseEntity.ok(dto);
    }

    @PostMapping()
    @Operation(summary = "создайте технологию",
            description = "создайте технологию, которая может быть применена для реализации проекта")
    public ResponseEntity<TechnologyFullDto> create(@Valid @RequestBody TechnologyCreateDto dto) {
        log.info(LogsUtils.getLogs(TechnologyController.class, "create", dto));
        var createdTechnology  = service.create(dto);
        log.info(LogsUtils.getResponseLogs(TechnologyController.class, "create", createdTechnology));
        return new ResponseEntity<>(createdTechnology, HttpStatus.CREATED);
    }

    @PutMapping()
    @Operation(summary = "обновите технологию",
            description = "обновите технологию, которая может быть применена для реализации проекта")
    public ResponseEntity<TechnologyFullDto> update(@Valid @RequestBody TechnologyUpdateDto dto) {
        log.info(LogsUtils.getLogs(TechnologyController.class, "update", dto));
        var updatedTechnology  = service.update(dto);
        log.info(LogsUtils.getResponseLogs(TechnologyController.class, "update", updatedTechnology));
        return ResponseEntity.ok(updatedTechnology);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "удалите технологию",
            description = "удалите технологию, которая может быть применена для реализации проекта")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        log.info(LogsUtils.getLogs(TechnologyController.class, "delete", id));
        var result  = service.delete(id);
        log.info(LogsUtils.getLogs(TechnologyController.class, "delete", result));
        return ResponseEntity.ok(result);
    }

}
