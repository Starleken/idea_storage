package ru.leafall.mainservice.repository;

import ru.leafall.mainservice.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    List<ProjectEntity> findAllByIdInAndIsVisible(Collection<Long> id, Boolean isVisible);
    List<ProjectEntity> findAllByIsVisible(Boolean isVisible);
}
