package com.protobin.authservice.entity.listener;

import com.protobin.authservice.entity.aware.CreatedAtTimestampAware;
import com.protobin.authservice.utils.TimeUtils;
import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.protobin.authservice.utils.TimeUtils.*;

@Component
public class TimestampListener {

    @PrePersist
    private void beforeSave(Object object) {
        if (object instanceof CreatedAtTimestampAware) {
            ((CreatedAtTimestampAware)object).setCreatedAt(getCurrentTimeFromUTC());
        }
    }
}
