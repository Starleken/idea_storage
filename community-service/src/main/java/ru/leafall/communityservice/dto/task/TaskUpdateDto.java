package ru.leafall.communityservice.dto.task;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class TaskUpdateDto {
    @Min(0)
    @NotNull(message = "id is null")
    private Long id;

    @NotEmpty
    @Length(min = 3, max = 30)
    private String header;
    @Max(255)
    private String description;
    @Min(0)
    private Long expiredAt;

    private Boolean isFinish;
}