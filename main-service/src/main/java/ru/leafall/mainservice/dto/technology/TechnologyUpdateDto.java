package ru.leafall.mainservice.dto.technology;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class TechnologyUpdateDto {
    @Min(value = 0, message = "id under (<) zero digit")
    @NotNull(message = "field id is cannot be null")
    private Integer id;

    @NotBlank(message = "field \"name\" is blank, please enter field")
    @Length(min = 1, max = 30)
    private String name;
}
