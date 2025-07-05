package ru.betrayal.fileservice.service;

import ru.betrayal.fileservice.dto.*;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface FileService {
    FileResponseDto findById(UUID id);
    PaginationResponse<FileResponseDto> findAll(FileFinderByIds dto, PaginationParams params);
    FileResponseDto upload(FileUploadDto dto) throws IOException;
    FileDownloadResponseDto download(String fileName);
    Integer delete(FileFinderByIds ids);
}
