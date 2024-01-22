package econovation.moodtracker.domain.dto.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class DiaryUpdateRequestDTO {
    // 일기 domain
    private Long diaryPk;
    private String content;

    // 감정 domain
    @Min(0) @Max(10)
    private Integer happiness;
    @Min(0) @Max(10)
    private Integer gloom;
    @Min(0) @Max(10)
    private Integer anxiety;
    @Min(0) @Max(10)
    private Integer stress;
    @Min(0) @Max(10)
    private Integer sleep;
}
