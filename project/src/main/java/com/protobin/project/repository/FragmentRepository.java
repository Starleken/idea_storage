package com.protobin.project.repository;

import com.protobin.project.entity.FragmentEntity;
import com.protobin.project.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FragmentRepository extends JpaRepository<FragmentEntity, UUID> {
    Page<FragmentEntity> findAllByProject(ProjectEntity project, Pageable pageable);
}
