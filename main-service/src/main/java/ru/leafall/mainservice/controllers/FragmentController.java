package ru.leafall.mainservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leafall.mainservice.dto.fragment.FragmentCreateDto;
import ru.leafall.mainservice.dto.fragment.FragmentFullDto;
import ru.leafall.mainservice.dto.fragment.FragmentShortDto;
import ru.leafall.mainservice.dto.fragment.FragmentUpdateDto;
import ru.leafall.mainservice.service.FragmentService;
import ru.leafall.mainservice.utils.LogsUtils;
import ru.leafall.mainstarter.utils.PaginationParams;

import java.util.List;

@RestController
@RequestMapping("v1/projects")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Fragments", description = "страницы, уровни ваших проектов")
public class FragmentController {

    private final FragmentService service;

    @GetMapping("/{projectId}/fragments")
    @Operation(summary = "поиск всех страниц (уровней) вашего проекта",
            description = "ищите все страницы (уровни), которые принадлежат вашей идеи")
    public ResponseEntity<List<FragmentShortDto>> findAll(@RequestParam(name = PaginationParams.LIMIT, defaultValue = "10") @Min(0) Integer limit,
                                                           @RequestParam(name = PaginationParams.PAGE, defaultValue = "0") @Min(0) Integer page,
                                                           @PathVariable @Min(0) Long projectId) {
        var params = new PaginationParams(limit, page);
        log.info(LogsUtils.getLogs(FragmentController.class, "findAll projectId: " + projectId, params));
        var result = service.findAllByProjectId(projectId, params);
        log.info(LogsUtils.getLogs(FragmentController.class, "findAll",  result.totalCount()));
        return ResponseEntity.ok()
                .header(PaginationParams.HEADER_TOTAL_COUNT, result.totalCount().toString())
                .body(result.items());
    }

    @GetMapping("/fragments/{id}")
    @Operation(summary = "поиск определенной страницы (уровня)",
            description = "позволяет найти по id определенный уровень")
    public ResponseEntity<FragmentFullDto> findById(@PathVariable Long id) {
        log.info(LogsUtils.getLogs(FragmentController.class, "findById", id));
        var fragment = service.findById(id);
        log.info(LogsUtils.getResponseLogs(FragmentController.class, "findById", fragment));
        return new ResponseEntity<>(fragment, HttpStatus.OK);
    }

    @PostMapping(path = "/fragments", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "создание уровня (страницы)",
            description = "позволяет добавить страницу (уровень) к вашему проекту")
    public ResponseEntity<FragmentShortDto> create(@ModelAttribute @Valid FragmentCreateDto dto) {
        log.info(LogsUtils.getLogs(FragmentController.class, "create", dto));
        var fragment = service.create(dto);
        log.info(LogsUtils.getResponseLogs(FragmentController.class, "create", fragment));
        return new ResponseEntity<>(fragment, HttpStatus.CREATED);
    }

    @PutMapping("/fragments")
    @Operation(summary = "обновление уровня (страницы)",
            description = "позволяет обновить страницу (уровень) у вашего проекта")
    public ResponseEntity<FragmentShortDto> update(@RequestBody @Valid FragmentUpdateDto dto) {
        log.info(LogsUtils.getLogs(FragmentController.class, "update", dto));
        var fragment = service.update(dto);
        log.info(LogsUtils.getResponseLogs(FragmentController.class, "update", fragment));
        return new ResponseEntity<>(fragment, HttpStatus.OK);
    }

    @DeleteMapping("/fragments/{id}")
    @Operation(summary = "удаление уровня (страницы)",
            description = "позволяет удалить страницу (уровень) у вашего проекта")
    public ResponseEntity<Long> delete(@PathVariable @Min(0) Long id) {
        log.info(LogsUtils.getLogs(FragmentController.class, "delete", id));
        var item = service.delete(id);
        log.info(LogsUtils.getResponseLogs(FragmentController.class, "delete", id));
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
