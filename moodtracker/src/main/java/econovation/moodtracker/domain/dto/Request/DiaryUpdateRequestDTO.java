package econovation.moodtracker.domain.dto.Request;

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
    private Long emotionPk;
    private Integer happiness;
    private Integer gloom;
    private Integer anxiety;
    private Integer stress;
    private Integer sleep;
}
