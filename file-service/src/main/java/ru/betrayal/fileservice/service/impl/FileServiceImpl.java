package ru.betrayal.fileservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.betrayal.fileservice.dto.FileDownloadResponseDto;
import ru.betrayal.fileservice.dto.FileFinderByIds;
import ru.betrayal.fileservice.dto.FileResponseDto;
import ru.betrayal.fileservice.dto.FileUploadDto;
import ru.betrayal.fileservice.entity.FileEntity;
import ru.betrayal.fileservice.mapper.FileMapper;
import ru.betrayal.fileservice.repository.FileRepository;
import ru.betrayal.fileservice.service.FileService;
import ru.betrayal.fileservice.service.S3Service;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;
import ru.leafall.mainstarter.utils.ThrowableUtils;

import java.awt.print.Pageable;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository repository;
    private final S3Service s3Service;
    private final FileMapper mapper;

    @Value("${cloud.aws.credentials.bucketName}")
    private String bucketName;

    @Override
    @Transactional
    public PaginationResponse<FileResponseDto> findAll(FileFinderByIds dto, PaginationParams params) {
        var sort = Sort.by("createdAt").ascending();
        var pageable = PageRequest.of(params.page(), params.limit(), sort);

        var files = repository.findAllByIdIn(dto.getIds(), pageable);
        var mappedFiles = files.map(mapper::mapToResponseDto);
        return new PaginationResponse<>(mappedFiles.getContent(), files.getTotalElements());
    }

    @Override
    @Transactional
    public FileResponseDto findById(UUID id) {
        var file = repository.findById(id)
                .orElseThrow(() ->
                        ThrowableUtils.getNotFoundException("File with id %d doesn't found", id
                        ));
        return mapper.mapToResponseDto(file);
    }

    @Override
    @Transactional
    public FileResponseDto upload(FileUploadDto dto) {
        var file = dto.getFile();
        var fileName = Objects.requireNonNull(file.getOriginalFilename()).replace("_", "-");
        var ticks = Instant.now().getEpochSecond();
        String keyName = String.format("files/%d_%s", ticks, fileName);

        String url = s3Service.uploadFile(file, keyName, bucketName);

        FileEntity newFile = mapper.mapToEntity(url.split("/")[1], fileName);
        newFile.setCreatedAt(ticks);

        var savedFileInBase = repository.save(newFile);

        return mapper.mapToResponseDto(savedFileInBase);
    }

    @Override
    @SneakyThrows
    public FileDownloadResponseDto download(String fileName) {
        String url = String.format("%s/%s", "files", fileName);
        InputStream stream = s3Service.downloadFile(bucketName, url);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        stream.transferTo(byteArrayOutputStream);

        var response = new FileDownloadResponseDto();
        response.setOutputStream(byteArrayOutputStream);
        response.setFileName(fileName);
        return response;
    }

    @Override
    @Transactional
    public Integer delete(FileFinderByIds dto) {
        repository.deleteAllByIdIn(dto.getIds());
        return dto.getIds().size();
    }
}
