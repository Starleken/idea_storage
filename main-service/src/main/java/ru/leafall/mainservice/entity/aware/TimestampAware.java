package ru.leafall.mainservice.entity.aware;

public interface TimestampAware {
    Long getCreatedAt();
    void setCreatedAt(Long createdAt);
}
