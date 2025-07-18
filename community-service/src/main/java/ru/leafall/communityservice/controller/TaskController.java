package ru.leafall.communityservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leafall.communityservice.dto.task.TaskCreateDto;
import ru.leafall.communityservice.dto.task.TaskResponseFullDto;
import ru.leafall.communityservice.dto.task.TaskUpdateDto;
import ru.leafall.communityservice.service.TaskService;
import ru.leafall.mainstarter.utils.PaginationParams;

import java.util.List;

@RestController
@RequestMapping("v1/participants")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping("{participantId}/tasks")
    public ResponseEntity<List<TaskResponseFullDto>> findAllByProjectId(@PathVariable @Min(0) Long participantId,
                                                                        @RequestParam(name = PaginationParams.PAGE, defaultValue = "0") @Min(0) Integer page,
                                                                        @RequestParam(name = PaginationParams.LIMIT, defaultValue = "5") @Min(1) Integer limit) {
        var pagination = new PaginationParams(limit, page);
        var result = service.findAllByParticipantId(participantId, pagination);
        return ResponseEntity.ok()
                .header(PaginationParams.HEADER_TOTAL_COUNT, result.totalCount().toString())
                .body(result.items());
    }

    @GetMapping("tasks/{id}")
    public ResponseEntity<TaskResponseFullDto> findById(@PathVariable @Min(0) Long id) {
        var result = service.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskResponseFullDto> create(@RequestBody @Valid TaskCreateDto dto) {
        var result = service.create(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/tasks")
    public ResponseEntity<TaskResponseFullDto> update(@RequestBody @Valid TaskUpdateDto dto) {
        var result = service.update(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<TaskResponseFullDto> delete(@PathVariable @Min(0) Long id) {
        var result = service.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
