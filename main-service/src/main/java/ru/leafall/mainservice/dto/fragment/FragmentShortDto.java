package ru.leafall.mainservice.dto.fragment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FragmentShortDto {
    private Long id;
    private String picture;
    private String description;
    private Long projectId;
}
