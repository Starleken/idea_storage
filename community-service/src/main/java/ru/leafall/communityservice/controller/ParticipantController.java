package ru.leafall.communityservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leafall.communityservice.dto.participant.ParticipantCreateDto;
import ru.leafall.communityservice.dto.participant.ParticipantResponseFullDto;
import ru.leafall.communityservice.dto.participant.ParticipantUpdateDto;
import ru.leafall.communityservice.service.ParticipantService;
import ru.leafall.mainstarter.model.Project;
import ru.leafall.mainstarter.utils.PaginationParams;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService service;

    @GetMapping("v1/users/{userId}/projects")
    public ResponseEntity<List<Project>> findAllByUserId(@PathVariable @Min(0) Long userId,
                                                         @RequestParam(name = PaginationParams.PAGE, defaultValue = "0") @Min(0) Integer page,
                                                         @RequestParam(name = PaginationParams.LIMIT, defaultValue = "5") @Min(1) Integer limit) {
        var params = new PaginationParams(limit, page);
        var result = service.findAllByUserId(userId, params);
        return ResponseEntity.ok()
                .header(PaginationParams.HEADER_TOTAL_COUNT, result.totalCount().toString())
                .body(result.items());
    }

    @GetMapping("v1/participants/{id}")
    public ResponseEntity<ParticipantResponseFullDto> findById(@PathVariable @Min(0) Long id) {
        var result = service.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("v1/users/{userId}/projects/{projectId}")
    public ResponseEntity<ParticipantResponseFullDto> create(@PathVariable @Min(0) Long projectId, @PathVariable @Min(0) Long userId) {
        var dto = ParticipantCreateDto.builder().projectId(projectId).userId(userId).build();
        var result = service.create(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("v1/participants")
    public ResponseEntity<ParticipantResponseFullDto> update(@RequestBody @Valid ParticipantUpdateDto dto) {
        var result = service.update(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("v1/participants/{id}")
    public ResponseEntity<ParticipantResponseFullDto> update(@PathVariable @Min(0) Long id) {
        var result = service.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
