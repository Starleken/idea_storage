package ru.leafall.mainservice.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.leafall.mainservice.entity.RelatedProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatedProjectRepository extends JpaRepository<RelatedProjectEntity, Long> {

    @Query("select rp from RelatedProjectEntity rp where rp.project.id=?1")
    Page<RelatedProjectEntity> findAllByProjectId(Long projectId, PageRequest request);
}
