package ru.leafall.communityservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tasks_steps")
public class StepEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    @Column(name = "finished_at", nullable = true)
    private Long finishedAt;
}
