package ru.betrayal.fileservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.betrayal.fileservice.entity.FileEntity;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, UUID> {
    Page<FileEntity> findAllByIdIn(Collection<UUID> ids, PageRequest pageable);
    void deleteAllByIdIn(Collection<UUID> ids);
}
