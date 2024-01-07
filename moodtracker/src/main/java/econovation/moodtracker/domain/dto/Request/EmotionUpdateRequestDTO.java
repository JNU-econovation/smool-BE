package econovation.moodtracker.domain.emotion.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class EmotionUpdateRequestDTO {
    private Long emotionPK;
    private Integer happiness;
    private Integer sadness;
    private Integer anxiety;
    private Integer stress;
    private Integer sleepTime;
    //DTO -> Entity = toEntity
    //Entity -> DTO = of
}