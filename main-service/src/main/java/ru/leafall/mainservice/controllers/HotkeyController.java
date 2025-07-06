package ru.leafall.mainservice.controllers;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leafall.mainservice.dto.hotkey.HotkeyCreateDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyFullDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyShortDto;
import ru.leafall.mainservice.dto.hotkey.HotkeyUpdateDto;
import ru.leafall.mainservice.service.HotkeyService;
import ru.leafall.mainstarter.utils.PaginationParams;

import java.util.List;

@RestController
@RequestMapping("v1/projects")
@RequiredArgsConstructor
public class HotkeyController {

    private final HotkeyService service;

    @GetMapping("/{projectId}/hotkeys")
    public ResponseEntity<List<HotkeyShortDto>> findAll(@RequestParam(name = PaginationParams.LIMIT, defaultValue = "10") @Min(0) Integer limit,
                                                        @RequestParam(name = PaginationParams.PAGE, defaultValue = "1") @Min(1) Integer page,
                                                        @PathVariable @Min(0) Long projectId
    ) {
        var params = new PaginationParams(limit, page);
        var result = service.findAllByProjectId(projectId, params);
        return ResponseEntity.ok()
                .header(PaginationParams.HEADER_TOTAL_COUNT, result.totalCount().toString())
                .body(result.items());
    }

    @GetMapping("/hotkeys/{id}")
    public ResponseEntity<HotkeyFullDto> findById(@PathVariable @Min(0) Long id) {
        var result = service.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/hotkeys")
    public ResponseEntity<HotkeyShortDto> create(@RequestBody HotkeyCreateDto dto) {
        var result = service.create(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/hotkeys")
    public ResponseEntity<HotkeyShortDto> update(@RequestBody HotkeyUpdateDto dto) {
        var result = service.update(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/hotkeys/{id}")
    public ResponseEntity<Long> update(@PathVariable @Min(0) Long id) {
        var result = service.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

