package ru.leafall.mainservice.repository;

import org.springframework.data.domain.Example;
import ru.leafall.mainservice.entity.TechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TechnologyRepository extends JpaRepository<TechnologyEntity, Integer> {
    Optional<TechnologyEntity> findByName(String name);
}
