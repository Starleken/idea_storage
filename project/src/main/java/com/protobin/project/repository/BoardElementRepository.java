package com.protobin.project.repository;

import com.protobin.project.entity.BoardElementEntity;
import com.protobin.project.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BoardElementRepository extends JpaRepository<BoardElementEntity, UUID> {

    List<BoardElementEntity> findAllByProject(ProjectEntity project);
}
