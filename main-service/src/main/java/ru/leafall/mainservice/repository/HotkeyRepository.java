package ru.leafall.mainservice.repository;

import ru.leafall.mainservice.entity.HotkeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotkeyRepository extends JpaRepository<HotkeyEntity, Long> {
    @Query("select hk from HotkeyEntity hk where hk.project.id = ?1")
    List<HotkeyEntity> findAllByProjectId(Long projectId);
}
