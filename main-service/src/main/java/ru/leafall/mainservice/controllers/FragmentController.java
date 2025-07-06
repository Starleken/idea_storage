package ru.leafall.mainservice.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leafall.mainservice.dto.fragment.FragmentCreateDto;
import ru.leafall.mainservice.dto.fragment.FragmentFullDto;
import ru.leafall.mainservice.dto.fragment.FragmentShortDto;
import ru.leafall.mainservice.dto.fragment.FragmentUpdateDto;
import ru.leafall.mainservice.service.FragmentService;
import ru.leafall.mainstarter.utils.PaginationParams;

import java.util.List;

@RestController
@RequestMapping("v1/projects")
@RequiredArgsConstructor
public class FragmentController {

    private final FragmentService service;

    @GetMapping("/{projectId}/fragments")
    public ResponseEntity<List<FragmentShortDto>> findAll(@RequestParam(name = PaginationParams.LIMIT, defaultValue = "10") @Min(0) Integer limit,
                                                           @RequestParam(name = PaginationParams.PAGE, defaultValue = "1") @Min(1) Integer page,
                                                           @PathVariable @Min(0) Long projectId
    ) {
        var params = new PaginationParams(limit, page);
        var result = service.findAllByProjectId(projectId, params);
        return ResponseEntity.ok()
                .header(PaginationParams.HEADER_TOTAL_COUNT, result.totalCount().toString())
                .body(result.items());
    }

    @GetMapping("/fragments/{id}")
    public ResponseEntity<FragmentFullDto> findById(@PathVariable Long id) {
        var fragment = service.findById(id);
        return new ResponseEntity<>(fragment, HttpStatus.OK);
    }

    @PostMapping(path = "/fragments", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<FragmentShortDto> create(@ModelAttribute @Valid FragmentCreateDto dto) {
        var fragment = service.create(dto);
        return new ResponseEntity<>(fragment, HttpStatus.CREATED);
    }

    @PutMapping("/fragments")
    public ResponseEntity<FragmentShortDto> update(@RequestBody @Valid FragmentUpdateDto dto) {
        var fragment = service.update(dto);
        return new ResponseEntity<>(fragment, HttpStatus.OK);
    }

    @DeleteMapping("/fragments/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        var item = service.delete(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
