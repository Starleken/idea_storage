package com.protobin.project.entity.listener;

import com.protobin.historyservicestarter.entity.HistoryAction;
import com.protobin.historyservicestarter.service.HistoryService;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
public class HistoryListener {

    @Autowired
    private HistoryService historyService;

    @PostPersist
    private void afterFirstSave(Object entity) {
        sendMessage(entity, HistoryAction.CREATED);
    }

    @PostUpdate
    private void afterAnyUpdate(Object entity) {
        sendMessage(entity, HistoryAction.UPDATED);
    }

    @PostRemove
    private void afterRemove(Object entity) {
        sendMessage(entity, HistoryAction.DELETED);
    }

    private void sendMessage(Object entity, HistoryAction action) {
        try {
            var entryId = String.valueOf(Instant.now().toEpochMilli());
            historyService.sendMessage(entity, entryId, action);
        } catch (Exception ex) {
            log.error("Exception on sending history", ex);
        }
    }
}
