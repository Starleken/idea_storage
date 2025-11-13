package com.protobin.project.entity;

import com.protobin.project.entity.aware.CreatedAtTimestampAware;
import com.protobin.project.entity.aware.UpdateAtTimestampAware;
import com.protobin.project.entity.enums.ProjectVisibleStatus;
import com.protobin.project.entity.listener.TimestampListener;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "projects")
@Data
@EqualsAndHashCode
@EntityListeners({TimestampListener.class})
public class ProjectEntity implements CreatedAtTimestampAware, UpdateAtTimestampAware {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", nullable = false)
    private  String title;

    @Column(name = "concept", nullable = false)
    private String concept;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status_visible", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectVisibleStatus visibleStatus;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @Column(name = "updated_at", nullable = false)
    private Long updatedAt;

    @Column(name = "deleted_at", nullable = true)
    private Long deletedAt;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = true)
    private OrganizationEntity organization;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<BoardElementEntity> boardElements = new ArrayList<>();
}
