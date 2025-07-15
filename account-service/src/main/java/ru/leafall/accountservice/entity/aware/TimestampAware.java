package ru.leafall.accountservice.entity.aware;

public interface TimestampAware {
    void setCreatedAt(Long createdAt);
    Long getCreatedAt();
}
