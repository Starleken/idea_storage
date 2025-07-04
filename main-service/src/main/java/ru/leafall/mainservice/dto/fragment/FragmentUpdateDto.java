package ru.leafall.mainservice.dto.fragment;

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
public class FragmentUpdateDto {

    @NotNull
    @Min(0)
    private Long id;

    @NotBlank
    @Length(min = 1, max = 2000)
    private String description;
}
