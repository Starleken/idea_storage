package ru.leafall.mainservice.dto.technology;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class TechnologyCreateDto {
    @NotBlank(message = "field \"name\" is blank, please enter field")
    @Length(min = 1, max = 30)
    private String name;
}
