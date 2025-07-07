package ru.leafall.mainservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leafall.mainservice.dto.project.related.RelatedProjectCreateDto;
import ru.leafall.mainservice.dto.project.related.RelatedProjectFullDto;
import ru.leafall.mainservice.dto.project.related.RelatedProjectShortDto;
import ru.leafall.mainservice.dto.project.related.RelatedProjectUpdateDto;
import ru.leafall.mainservice.service.RelatedProjectService;
import ru.leafall.mainstarter.exception.ErrorDto;
import ru.leafall.mainstarter.utils.PaginationParams;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/projects")
@Tag(name = "related projects", description = "здесь хранятся похожие проекты на ваш проект!")
public class RelatedProjectController {

    private final RelatedProjectService service;

    @GetMapping("/{projectId}/related")
    @Operation(description = "Один похожий продукт")
    public ResponseEntity<List<RelatedProjectShortDto>> findAll(@RequestParam(name = PaginationParams.LIMIT, defaultValue = "10") @Min(0) Integer limit,
                                                                @RequestParam(name = PaginationParams.PAGE, defaultValue = "0") @Min(0) Integer page,
                                                                @PathVariable @Min(0) Long projectId
    ) {
        var params = new PaginationParams(limit, page);
        var result = service.findAllByProjectId(projectId, params);
        return ResponseEntity.ok()
                .header(PaginationParams.HEADER_TOTAL_COUNT, result.totalCount().toString())
                .body(result.items());
    }

    @GetMapping("/related/{id}")
    @Operation(description = "Один похожий продукт")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "404", description = "Object with id is not found", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "200", description = "Object with id is found and exited"),
    })
    public ResponseEntity<RelatedProjectFullDto> findById(@PathVariable @Min(0) Long id) {
        var result = service.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/related")
    @Operation(description = "Добавление похожего продукта к вашему")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "404", description = "Object with id is not found", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "400", description = "Your parameters entered is not valid", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "201", description = "Object created"),
    })
    public ResponseEntity<RelatedProjectShortDto> create(@RequestBody @Valid RelatedProjectCreateDto dto) {
        var result = service.create(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/related")
    @Operation(description = "Обновление похожего продукта")
    public ResponseEntity<RelatedProjectShortDto> update(@RequestBody @Valid RelatedProjectUpdateDto dto) {
        var result = service.update(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/related/{id}")
    @Operation(description = "Удаление похожего продукта")
    public ResponseEntity<Long> delete(@PathVariable @Min(0) Long id) {
        var result = service.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
