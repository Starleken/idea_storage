package ru.leafall.communityservice.dto.step;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class StepCreateDto {
    @Min(0)
    @NotNull(message = "taskId is null")
    private Long taskId;

    @NotEmpty
    @Length(min = 3, max = 30)
    private String text;
}
