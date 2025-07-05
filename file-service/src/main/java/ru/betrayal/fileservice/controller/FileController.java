package ru.betrayal.fileservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.betrayal.fileservice.dto.FileFinderByIds;
import ru.betrayal.fileservice.dto.FileResponseDto;
import ru.betrayal.fileservice.dto.FileUploadDto;
import ru.betrayal.fileservice.service.FileService;
import ru.leafall.mainstarter.utils.PaginationParams;
import static ru.leafall.mainstarter.utils.PaginationParams.PAGE;
import static ru.leafall.mainstarter.utils.PaginationParams.LIMIT;
import static ru.leafall.mainstarter.utils.PaginationParams.HEADER_TOTAL_COUNT;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/files")
public class FileController {

    private final FileService service;

    @GetMapping(path = "")
    public ResponseEntity<List<FileResponseDto>> findAllByIdIn(@RequestBody FileFinderByIds dto,
                                                               @RequestParam(name = PAGE, defaultValue = "0") Integer page,
                                                               @RequestParam(name = LIMIT, defaultValue = "10") Integer limit
    ) {
        var result = service.findAll(dto, new PaginationParams(limit, page));
        return ResponseEntity.ok()
                .header(HEADER_TOTAL_COUNT, result.totalCount().toString())
                .body(result.items());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FileResponseDto> findById(@PathVariable UUID id) {
        var result = service.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/{fileName}/download")
    @SneakyThrows
    public ResponseEntity<byte[]> download(@PathVariable String fileName) {
        var result = service.download(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + result.getFileName() + "\"")
                .body(result.getOutputStream().toByteArray());
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @SneakyThrows
    public ResponseEntity<FileResponseDto> upload(@ModelAttribute @Valid FileUploadDto dto) {
        var result = service.upload(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Integer> delete(@RequestBody @Valid FileFinderByIds ids) {
        var result = service.delete(ids);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
