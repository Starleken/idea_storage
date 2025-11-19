package com.protobin.historyservice.repository;

import com.protobin.historyservice.entity.HistoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends MongoRepository<HistoryEntity, Long> {
}
