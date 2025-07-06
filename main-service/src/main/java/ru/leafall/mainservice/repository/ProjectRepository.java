package ru.leafall.mainservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.leafall.mainservice.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    Page<ProjectEntity> findAllByIdInAndIsVisible(Collection<Long> id, Boolean isVisible, Pageable pageable);
    Page<ProjectEntity> findAllByIsVisible(Boolean isVisible, Pageable pageable);
}
