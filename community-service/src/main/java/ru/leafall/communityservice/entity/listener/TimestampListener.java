package ru.leafall.communityservice.entity.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.stereotype.Component;
import ru.leafall.communityservice.entity.aware.TimestampAware;

import java.time.Instant;

@Component
public class TimestampListener {

    @PrePersist
    public void beforeSave(Object object) {
        if (object instanceof TimestampAware timestamp) {
            var createdAt = Instant.now().toEpochMilli();
            timestamp.setCreatedAt(createdAt);
            timestamp.setUpdatedAt(createdAt);
        }
    }

    @PreUpdate
    public void beforeUpdate(Object object) {
        if (object instanceof TimestampAware timestamp) {
            timestamp.setUpdatedAt(Instant.now().toEpochMilli());
        }
    }
}
