package ru.leafall.fileservicestarter.dto;

import java.util.Objects;
import java.util.UUID;

public class FileResponseDto {
    private UUID id;
    private String fileName;
    private String url;
    private Long createdAt;

    public FileResponseDto(UUID id, String fileName, String url, Long createdAt) {
        this.id = id;
        this.fileName = fileName;
        this.url = url;
        this.createdAt = createdAt;
    }

    public FileResponseDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "FileResponseDto{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileResponseDto that = (FileResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(fileName, that.fileName) && Objects.equals(url, that.url) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileName, url, createdAt);
    }
}
