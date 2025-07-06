package ru.leafall.mainservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.leafall.mainservice.entity.ProjectTechnologyEntity;
import ru.leafall.mainservice.entity.primarykeys.ProjectTechnologyPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectTechnologyRepository extends JpaRepository<ProjectTechnologyEntity, ProjectTechnologyPK> {

    @Query(value = "SELECT p FROM ProjectTechnologyEntity p WHERE p.id.project.id = ?1", nativeQuery = false)
    Page<ProjectTechnologyEntity> findAllByProjectId(Long projectId, PageRequest request);
}
