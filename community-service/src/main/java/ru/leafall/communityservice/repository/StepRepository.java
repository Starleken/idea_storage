package ru.leafall.communityservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leafall.communityservice.entity.StepEntity;

@Repository
public interface StepRepository extends JpaRepository<StepEntity, Long> {
    Page<StepEntity> findAllByTaskId(Long taskId, Pageable pageable);
}
