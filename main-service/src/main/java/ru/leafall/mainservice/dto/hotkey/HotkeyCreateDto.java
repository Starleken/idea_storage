package ru.leafall.mainservice.dto.hotkey;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotkeyCreateDto {

    @Length(min = 1, max = 30)
    @NotBlank
    private String name;

    @Length(min = 1, max = 255)
    @NotBlank
    private String description;

    @Min(0)
    @NotNull
    private Long projectId;

}
