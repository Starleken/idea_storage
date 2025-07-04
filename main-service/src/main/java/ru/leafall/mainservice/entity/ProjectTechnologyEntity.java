package ru.leafall.mainservice.entity;

import ru.leafall.mainservice.entity.primarykeys.ProjectTechnologyPK;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "projects_techonologies")
public class ProjectTechnologyEntity {

    @Id
    private ProjectTechnologyPK id;
}
