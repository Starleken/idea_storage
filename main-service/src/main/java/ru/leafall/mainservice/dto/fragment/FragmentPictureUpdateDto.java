package ru.leafall.mainservice.dto.fragment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FragmentPictureUpdateDto {
    @NotNull
    @Min(0)
    private Long id;

    @NotNull
    private MultipartFile picture;
}
