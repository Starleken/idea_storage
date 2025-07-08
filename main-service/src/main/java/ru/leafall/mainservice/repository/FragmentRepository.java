package ru.leafall.mainservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.leafall.mainservice.entity.FragmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FragmentRepository extends JpaRepository<FragmentEntity, Long> {
    @Query(value = "select f from FragmentEntity f where f.project.id=?1", nativeQuery = false)
    Page<FragmentEntity> findAllByProjectId(Long projectId, PageRequest request);
}
