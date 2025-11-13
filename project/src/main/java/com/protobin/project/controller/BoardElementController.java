package com.protobin.project.controller;

import com.protobin.project.dto.boardElement.BoardElementCreateDto;
import com.protobin.project.dto.boardElement.BoardElementResponseDto;
import com.protobin.project.dto.boardElement.BoardElementUpdateDto;
import com.protobin.project.exception.annotation.ApiResponseBadRequest;
import com.protobin.project.exception.annotation.ApiResponseForbidden;
import com.protobin.project.exception.annotation.ApiResponseNoAuth;
import com.protobin.project.exception.annotation.ApiResponseNotFound;
import com.protobin.project.service.BoardElementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "v1/elements")
@Tag(name = "Board elements", description = "Необходимые руты для работы с элементами доски")
@RequiredArgsConstructor
public class BoardElementController {

    private final BoardElementService boardElementService;

    @GetMapping("/project/{projectId}")
    @Operation(
            summary = "Получить список элементов по UUID проекта",
            description = "Получить список элементов",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Список элементов доски проекта успешно получен")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @ApiResponseNoAuth
    @ApiResponseForbidden
    public ResponseEntity<List<BoardElementResponseDto>> findAllByProject(@PathVariable UUID projectId) {
        var response = boardElementService.findAllByProject(projectId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @Operation(
            summary = "Создать элемент доски",
            description = "Создать элемент доски",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "201", description = "Элемент успешно создан")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @ApiResponseNoAuth
    @ApiResponseForbidden
    public ResponseEntity<BoardElementResponseDto> create(@RequestBody BoardElementCreateDto createDto) {
        var response = boardElementService.create(createDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(
            summary = "Обновить элемент доски по UUID",
            description = "Обновить элемент доски",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Элемент успешно обновлён")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @ApiResponseNoAuth
    @ApiResponseForbidden
    public ResponseEntity<BoardElementResponseDto> update(@RequestBody BoardElementUpdateDto updateDto) {
        var response = boardElementService.update(updateDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить элемент с доски по UUID",
            description = "удалить элемент доски",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Элемент успешно удалён")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @ApiResponseNoAuth
    @ApiResponseForbidden
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        boardElementService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    @Operation(
            summary = "Удалить список элементов по массиву UUID",
            description = "удалить список элементов",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Элементы успешно удалены")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @ApiResponseNoAuth
    @ApiResponseForbidden
    public ResponseEntity<Void> deleteByIds(@RequestBody List<UUID> ids) {
        boardElementService.deleteByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
