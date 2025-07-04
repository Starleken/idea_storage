package ru.leafall.mainservice.dto.fragment;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.leafall.mainservice.dto.project.ProjectFullDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FragmentFullDto {
    private Long id;
    private String picture;
    private String description;
    private ProjectFullDto project;
}
