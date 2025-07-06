package ru.leafall.mainservice.controllers;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leafall.mainservice.dto.project.technology.ProjectTechnologiesDto;
import ru.leafall.mainservice.dto.project.technology.ProjectTechnologyCreateDto;
import ru.leafall.mainservice.dto.project.technology.ProjectTechnologyShortDto;
import ru.leafall.mainservice.service.ProjectTechnologyService;
import ru.leafall.mainstarter.utils.PaginationParams;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/projects/{projectId}/technologies")
public class ProjectTechnologyController {

    private final ProjectTechnologyService service;

    @GetMapping
    public ResponseEntity<ProjectTechnologiesDto> findAllTechnologies(@PathVariable @Min(0) Long projectId,
                                                                      @RequestParam(name = PaginationParams.LIMIT, defaultValue = "10") @Min(0) Integer limit,
                                                                      @RequestParam(name = PaginationParams.PAGE, defaultValue = "1") @Min(1) Integer page
    ) {
        var params = new PaginationParams(limit, page);
        var result = service.findAllTechnologiesByProjectId(projectId, params);
        return ResponseEntity.ok()
                .header(PaginationParams.HEADER_TOTAL_COUNT, result.getTotalCount().toString())
                .body(result);
    }

    @PostMapping("/{name}")
    public ResponseEntity<ProjectTechnologyShortDto> create(@PathVariable @NotEmpty String name, @PathVariable @Min(0) Long projectId) {
        var dto = new ProjectTechnologyCreateDto();
        dto.setProjectId(projectId);
        dto.setTechnology(name);
        var result = service.create(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{technologyId}")
    public ResponseEntity<Integer> delete(@PathVariable @Min(0) Integer technologyId, @PathVariable @Min(0) Long projectId) {
        service.delete(projectId, technologyId);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

}
