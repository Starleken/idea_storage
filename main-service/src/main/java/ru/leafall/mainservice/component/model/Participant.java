package ru.leafall.mainservice.component.model;

import lombok.Data;

@Data
public class Participant {
    private Long id;
    private Long userId;
    private Long projectId;
    private Boolean isPrivileged;
}
