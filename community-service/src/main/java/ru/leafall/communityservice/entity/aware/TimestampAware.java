package ru.leafall.communityservice.entity.aware;

public interface TimestampAware {
    void setCreatedAt(Long createdAt);
    void setUpdatedAt(Long updatedAt);
}
