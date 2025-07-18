package ru.leafall.communityservice.dto.task;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class TaskCreateDto {
    @NotEmpty
    @Length(min = 3, max = 30)
    private String header;

    @Max(255)
    private String description;

    @Min(0)
    private Long expiredAt;
}
