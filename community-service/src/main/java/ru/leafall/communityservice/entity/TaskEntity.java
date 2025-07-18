package ru.leafall.communityservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.leafall.communityservice.entity.aware.TimestampAware;

import java.util.List;

@Entity
@Data
@Table(name = "tasks")
public class TaskEntity implements TimestampAware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "header", nullable = false)
    private String header;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @Column(name = "updated_at", nullable = true)
    private Long updatedAt;

    @Column(name = "expired_at", nullable = true)
    private Long expiredAt;

    @Column(name = "finished_at", nullable = true)
    private Long finishedAt;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private ParticipantEntity participant;

    @OneToMany
    @JoinColumn(name = "task_id", nullable = false)
    private List<StepEntity> steps;
}
