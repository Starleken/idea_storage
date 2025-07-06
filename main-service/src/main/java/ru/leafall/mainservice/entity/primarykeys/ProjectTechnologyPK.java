package ru.leafall.mainservice.entity.primarykeys;

import jakarta.persistence.*;
import ru.leafall.mainservice.entity.ProjectEntity;
import ru.leafall.mainservice.entity.TechnologyEntity;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class ProjectTechnologyPK implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technology_id", nullable = false)
    private TechnologyEntity technology;

}
