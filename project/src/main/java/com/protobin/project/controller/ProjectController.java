package com.protobin.project.controller;

import com.protobin.project.dto.ProjectCreateDto;
import com.protobin.project.dto.ProjectResponseDto;
import com.protobin.project.dto.ProjectUpdateDto;
import com.protobin.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> findById(@PathVariable UUID id) {
        log.info("Project find by id request:{}", id);
        var found = projectService.findById(id);
        log.info(found.toString());

        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDto> create(@RequestBody ProjectCreateDto createDto) {
        log.info("Project create request");
        var created = projectService.create(createDto);
        log.info(created.toString());

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProjectResponseDto> update(@RequestBody ProjectUpdateDto updateDto) {
        log.info("Project update request");
        var updated = projectService.update(updateDto);
        log.info(updated.toString());

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        log.info("Project delete request: {}", id);
        projectService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
