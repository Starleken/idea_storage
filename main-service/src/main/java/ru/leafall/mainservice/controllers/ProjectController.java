package ru.leafall.mainservice.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import ru.leafall.mainservice.dto.project.ProjectCreateDto;
import ru.leafall.mainservice.dto.project.ProjectFullDto;
import ru.leafall.mainservice.dto.project.ProjectUpdateDto;
import ru.leafall.mainservice.entity.TechnologyEntity;
import ru.leafall.mainservice.repository.TechnologyRepository;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.leafall.mainservice.service.ProjectService;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @GetMapping()
    public ResponseEntity<List<ProjectFullDto>> getProjects(@RequestParam(name = PaginationParams.LIMIT, defaultValue = "10") @Min(0) Integer limit,
                                                            @RequestParam(name = PaginationParams.PAGE, defaultValue = "0") @Min(0) Integer page,
                                                            @RequestParam(name = "ids", required = false) Set<Long> ids
    ) {
        PaginationResponse<ProjectFullDto> result = null;
        var params = new PaginationParams(limit, page);

        if(ids == null || ids.isEmpty()) {
            result = service.findAll(params);
        } else {
            result = service.findAllByIds(ids, params);
        }

        return ResponseEntity.ok()
                .header(PaginationParams.HEADER_TOTAL_COUNT, result.totalCount().toString())
                .body(result.items());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectFullDto> findById(@PathVariable @Min(0) Long id) {
        var item = service.findById(id);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProjectFullDto> create(@RequestBody @Valid ProjectCreateDto dto) {
        var item = service.create(dto);

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProjectFullDto> update(@RequestBody @Valid ProjectUpdateDto dto) {
        var item = service.update(dto);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> delete(@PathVariable @Min(0) Long id) {
        var item = service.delete(id);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
