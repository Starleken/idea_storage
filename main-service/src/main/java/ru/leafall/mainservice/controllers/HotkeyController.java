package ru.leafall.mainservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leafall.mainservice.dto.hotkey.HotkeyCreateDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyFullDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyShortDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyUpdateDto;
import ru.leafall.mainservice.service.HotkeyService;
import ru.leafall.mainservice.utils.LogsUtils;
import ru.leafall.mainstarter.utils.PaginationParams;

import java.util.List;

@RestController
@RequestMapping("v1/projects")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "hotkeys", description = "все ваши горячие клавиши, которые использует проекты")
public class HotkeyController {

    private final HotkeyService service;

    @GetMapping("/{projectId}/hotkeys")
    @Operation(summary = "поиск всех горячих клавиш вашего проекта",
            description = "ищите все горячие клавиши, которые принадлежат вашей идеи")
    public ResponseEntity<List<HotkeyShortDto>> findAll(@RequestParam(name = PaginationParams.LIMIT, defaultValue = "10") @Min(0) Integer limit,
                                                        @RequestParam(name = PaginationParams.PAGE, defaultValue = "0") @Min(0) Integer page,
                                                        @PathVariable @Min(0) Long projectId
    ) {
        var params = new PaginationParams(limit, page);
        log.info(LogsUtils.getLogs(HotkeyController.class, "findAll projectId: " + projectId, params));
        var result = service.findAllByProjectId(projectId, params);
        log.info(LogsUtils.getLogs(HotkeyController.class, "findAll",  result.totalCount()));
        return ResponseEntity.ok()
                .header(PaginationParams.HEADER_TOTAL_COUNT, result.totalCount().toString())
                .body(result.items());
    }

    @GetMapping("/hotkeys/{id}")
    @Operation(summary = "поиск определенной горячей клавиши",
            description = "позволяет найти по id определенную горячую клавишу")
    public ResponseEntity<HotkeyFullDto> findById(@PathVariable @Min(0) Long id) {
        log.info(LogsUtils.getLogs(HotkeyController.class, "findById", id));
        var result = service.findById(id);
        log.info(LogsUtils.getResponseLogs(HotkeyController.class, "findById", result));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/hotkeys")
    @Operation(summary = "создание горячей клавиши",
            description = "позволяет добавить горячую клавишу к вашему проекту")
    public ResponseEntity<HotkeyShortDto> create(@RequestBody @Valid HotkeyCreateDto dto) {
        log.info(LogsUtils.getLogs(HotkeyController.class, "create", dto));
        var result = service.create(dto);
        log.info(LogsUtils.getResponseLogs(HotkeyController.class, "create", result));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/hotkeys/list")
    @Operation(summary = "создание списка горячих клавиш",
            description = "позволяет добавить несколько горячих клавиш к вашему проекту")
    public ResponseEntity<List<HotkeyShortDto>> create(@RequestBody @Valid List<HotkeyCreateDto> dtos) {
        log.info(LogsUtils.getLogs(HotkeyController.class, "create list", dtos.size()));
        var result = service.create(dtos);
        log.info(LogsUtils.getResponseLogs(HotkeyController.class, "create list", result.size()));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/hotkeys")
    @Operation(summary = "изменение горячей клавиши",
            description = "позволяет изменить горячую клавишу у вашего проекта")
    public ResponseEntity<HotkeyShortDto> update(@RequestBody @Valid HotkeyUpdateDto dto) {
        log.info(LogsUtils.getLogs(HotkeyController.class, "update", dto));
        var result = service.update(dto);
        log.info(LogsUtils.getResponseLogs(HotkeyController.class, "update", result));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/hotkeys/{id}")
    @Operation(summary = "удаление горячей клавиши",
            description = "позволяет удалить горячую клавишу у вашего проекта")
    public ResponseEntity<Long> update(@PathVariable @Min(0) Long id) {
        log.info(LogsUtils.getLogs(HotkeyController.class, "delete", id));
        var result = service.delete(id);
        log.info(LogsUtils.getResponseLogs(HotkeyController.class, "delete", result));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

