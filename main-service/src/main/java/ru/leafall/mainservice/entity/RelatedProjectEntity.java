package ru.leafall.mainservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "related_projects")
@Data
public class RelatedProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "is_moderated", nullable = false)
    private Boolean isModerated;

    @Column(name = "strong_side", nullable = false)
    private String strongSide;

    @Column(name = "weak_side", nullable = false)
    private String weakSide;
}
