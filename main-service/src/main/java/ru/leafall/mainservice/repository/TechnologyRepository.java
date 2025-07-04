package ru.leafall.mainservice.repository;

import ru.leafall.mainservice.entity.TechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TechnologyRepository extends JpaRepository<TechnologyEntity, Integer> {

}
