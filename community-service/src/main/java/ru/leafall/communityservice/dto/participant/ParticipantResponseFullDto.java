package ru.leafall.communityservice.dto.participant;

import lombok.Data;

@Data
public class ParticipantResponseFullDto {
    private Long id;
    private Long userId;
    private Long projectId;
    private Boolean isPrivileged;
}
