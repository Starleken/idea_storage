package ru.leafall.fileservicestarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.leafall.fileservicestarter.dto.FileResponseDto;
import ru.leafall.fileservicestarter.dto.FileUploadDto;
import org.springframework.core.io.Resource;


import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.MediaType.*;

public class FileService {

    private final String fileServiceUrl;
    private final RestTemplate template = new RestTemplate();

    public FileService(String fileServiceUrl) {
        this.fileServiceUrl = fileServiceUrl;
    }

    public FileResponseDto upload(MultipartFile file) {
        try {
            String url = String.format("%s/%s", fileServiceUrl, "upload");

            HttpHeaders headers = generateHeaders(MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> body
                    = new LinkedMultiValueMap<>();
            Resource resource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };
            body.add("file", resource);
            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
            var response = template.exchange(url, POST, request, FileResponseDto.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<FileResponseDto> findAllByIds(Set<UUID> ids) {
        String url = String.format("%s/%s", fileServiceUrl, "upload");

        HttpHeaders headers = generateHeaders(APPLICATION_JSON);
        HttpEntity<Set<UUID>> request = new HttpEntity<>(ids, headers);
        var response = template.exchange(url, GET, request, List.class);
        return response.getBody();
    }

    public Integer delete(UUID id) {
        String url = String.format("%s/{id}", fileServiceUrl);

        template.delete(url, id);
        return 1;
    }

    public FileResponseDto findById(UUID id) {
        String url = String.format("%s/{id}", fileServiceUrl);

        var response = template.getForEntity(url, FileResponseDto.class, id);
        return response.getBody();
    }

    private HttpHeaders generateHeaders(MediaType type) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(type);
        return httpHeaders;
    }
}
