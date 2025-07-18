package ru.leafall.communityservice.dto.participant;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParticipantCreateDto {
    @Min(0)
    @NotNull(message = "userId is null")
    private Long userId;

    @Min(0)
    @NotNull(message = "projectId is null")
    private Long projectId;
}
