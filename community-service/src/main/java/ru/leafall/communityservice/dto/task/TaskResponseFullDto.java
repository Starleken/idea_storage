package ru.leafall.communityservice.dto.task;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import ru.leafall.communityservice.entity.StepEntity;

import java.util.List;

@Data
public class TaskResponseFullDto {
    private Long id;
    private String header;
    private String description;
    private Long createdAt;
    private Long updatedAt;
    private Long expiredAt;
    private Long finishedAt;
    private List<StepEntity> steps;
}
