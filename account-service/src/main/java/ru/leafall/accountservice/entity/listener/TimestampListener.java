package ru.leafall.accountservice.entity.listener;

import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;
import ru.leafall.accountservice.entity.aware.TimestampAware;
import ru.leafall.accountservice.utils.TimeUtils;

import java.time.Instant;

@Component
public class TimestampListener {

    @PrePersist
    private void beforePostInBase(Object obj) {
        if(obj instanceof TimestampAware) {
            ((TimestampAware) obj).setCreatedAt(TimeUtils.getCurrentTimeFromUTC());
        }
    }
}
