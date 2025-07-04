package ru.leafall.mainservice.entity.primarykeys;

import ru.leafall.mainservice.entity.ProjectEntity;
import ru.leafall.mainservice.entity.TechnologyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class ProjectTechnologyPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "technology_id", nullable = false)
    private TechnologyEntity technology;

}
