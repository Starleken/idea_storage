package ru.leafall.mainservice.entity.listener;

import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;
import ru.leafall.mainservice.entity.aware.TimestampAware;

import java.time.Instant;

@Component
public class TimestampListener {

    @PrePersist
    private void beforeSave(Object object) {
        if(object instanceof TimestampAware) {
            ((TimestampAware) object).setCreatedAt(Instant.now().getEpochSecond());
        }
    }
}
