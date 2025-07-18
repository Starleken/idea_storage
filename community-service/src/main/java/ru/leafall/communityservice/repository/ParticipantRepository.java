package ru.leafall.communityservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leafall.communityservice.entity.ParticipantEntity;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
    Page<ParticipantEntity> findAllByUserId(Long userId, Pageable pageable);
}
