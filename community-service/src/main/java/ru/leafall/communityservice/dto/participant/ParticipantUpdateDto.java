package ru.leafall.communityservice.dto.participant;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParticipantUpdateDto {
    @Min(0)
    @NotNull(message = "id is null")
    private Long id;

    @NotNull(message = "isPrivileged is null")
    private Boolean isPrivileged;
}
