package ru.leafall.communityservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leafall.communityservice.entity.ParticipantEntity;
import ru.leafall.communityservice.entity.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Page<TaskEntity> findAllByParticipant(ParticipantEntity participant, Pageable pageable);
}
