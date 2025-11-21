package com.protobin.project.entity;

import com.protobin.project.entity.aware.CreatedAtTimestampAware;
import com.protobin.project.entity.listener.TimestampListener;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tags")
@Data
@EntityListeners({TimestampListener.class})
public class TagEntity implements CreatedAtTimestampAware {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;
}
