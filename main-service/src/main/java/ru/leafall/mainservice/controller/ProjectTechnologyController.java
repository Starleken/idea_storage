package ru.leafall.mainservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leafall.mainservice.dto.project.technology.ProjectTechnologiesDto;
import ru.leafall.mainservice.dto.project.technology.ProjectTechnologyCreateDto;
import ru.leafall.mainservice.dto.project.technology.ProjectTechnologyShortDto;
import ru.leafall.mainservice.service.ProjectTechnologyService;
import ru.leafall.mainservice.utils.LogsUtils;
import ru.leafall.mainstarter.utils.PaginationParams;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/projects/{projectId}/technologies")
@Slf4j
@Tag(name = "projects technologies", description = "технологии, которые используется в ваших проектах")
public class ProjectTechnologyController {

    private final ProjectTechnologyService service;

    @GetMapping
    @Operation(summary = "все технологии",
            description = "найди все технологии для реализации проекта")
    public ResponseEntity<ProjectTechnologiesDto> findAllTechnologies(@PathVariable @Min(0) Long projectId,
                                                                      @RequestParam(name = PaginationParams.LIMIT, defaultValue = "10") @Min(0) Integer limit,
                                                                      @RequestParam(name = PaginationParams.PAGE, defaultValue = "0") @Min(0) Integer page
    ) {
        var params = new PaginationParams(limit, page);
        log.info(LogsUtils.getLogs(ProjectTechnologyController.class, "findAllTechnologies projectId " + projectId, params));
        var result = service.findAllTechnologiesByProjectId(projectId, params);
        log.info(LogsUtils.getResponseLogs(ProjectTechnologyController.class, "findAllTechnologies projectId " + projectId, result.getTotalCount()));
        return ResponseEntity.ok()
                .header(PaginationParams.HEADER_TOTAL_COUNT, result.getTotalCount().toString())
                .body(result);
    }

    @PostMapping("/{name}")
    @Operation(summary = "добавь технологию",
            description = "добавляет новую технологию к проекту")
    public ResponseEntity<ProjectTechnologyShortDto> create(@PathVariable @NotEmpty String name, @PathVariable @Min(0) Long projectId) {
        var dto = new ProjectTechnologyCreateDto();
        dto.setProjectId(projectId);
        dto.setTechnology(name);
        log.info(LogsUtils.getLogs(ProjectTechnologyController.class, "create", dto));
        var result = service.create(dto);
        log.info(LogsUtils.getResponseLogs(ProjectTechnologyController.class, "create", result));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{technologyId}")
    @Operation(summary = "удаляет технологию",
            description = "удаляет технологию необходимую для реализации")
    public ResponseEntity<Integer> delete(@PathVariable @Min(0) Integer technologyId, @PathVariable @Min(0) Long projectId) {
        log.info(LogsUtils.getLogs(ProjectTechnologyController.class, "delete", String.format("technologyId: %d, projectId: %d", technologyId, projectId)));
        service.delete(projectId, technologyId);
        log.info(LogsUtils.getResponseLogs(ProjectTechnologyController.class, "delete", 1));
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

}
