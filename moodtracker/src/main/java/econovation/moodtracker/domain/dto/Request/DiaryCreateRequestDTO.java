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
public class DiaryCreateRequestDTO {
    // 일기 domain
    private Long userPK;
    private String content;

    // 감정 domain
    private Integer happiness;
    private Integer sadness;
    private Integer anxiety;
    private Integer stress;
    private Integer sleepTime;
}
