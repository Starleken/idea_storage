package ru.leafall.communityservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leafall.communityservice.dto.step.StepCreateDto;
import ru.leafall.communityservice.dto.step.StepFullDto;
import ru.leafall.communityservice.dto.step.StepUpdateDto;
import ru.leafall.communityservice.dto.task.TaskResponseFullDto;
import ru.leafall.communityservice.service.StepService;
import ru.leafall.mainstarter.utils.PaginationParams;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/tasks")
public class StepController {

    private final StepService service;

    @GetMapping("{taskId}/steps")
    public ResponseEntity<List<StepFullDto>> findAllByTaskId(@PathVariable @Min(0) Long taskId,
                                                             @RequestParam(name = PaginationParams.PAGE, defaultValue = "0") @Min(0) Integer page,
                                                             @RequestParam(name = PaginationParams.LIMIT, defaultValue = "5") @Min(1) Integer limit) {
        var pagination = new PaginationParams(limit, page);
        var result = service.findAllByTaskId(taskId, pagination);
        return ResponseEntity.ok()
                .header(PaginationParams.HEADER_TOTAL_COUNT, result.totalCount().toString())
                .body(result.items());
    }

    @GetMapping("/steps/{id}")
    public ResponseEntity<StepFullDto> findById(@PathVariable @Min(0) Long id) {
        var result = service.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/steps/")
    public ResponseEntity<StepFullDto> create(@RequestBody @Valid StepCreateDto dto) {
        var result = service.create(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/steps/")
    public ResponseEntity<StepFullDto> update(@RequestBody @Valid StepUpdateDto dto) {
        var result = service.update(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/steps/{id}")
    public ResponseEntity<StepFullDto> delete(@PathVariable @Min(0) Long id) {
        var result = service.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
