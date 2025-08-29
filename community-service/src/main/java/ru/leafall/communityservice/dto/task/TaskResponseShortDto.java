package ru.leafall.communityservice.dto.task;

import lombok.Data;

@Data
public class TaskResponseShortDto {
    private Long id;
    private String header;
    private String description;
    private Long createdAt;
    private Long updatedAt;
    private Long expiredAt;
    private Long finishedAt;
    private Long countCompletedSteps;
    private Long countAllSteps;
}
