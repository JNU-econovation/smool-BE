package econovation.moodtracker.domain.emotion.DTO.Request;

import econovation.moodtracker.domain.diary.Diary;
import econovation.moodtracker.domain.emotion.Emotion;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.*;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class EmotionCreateRequestDTO {
    private Long userPK;
    private LocalTime localTime;
    private Integer happiness;
    private Integer sadness;
    private Integer anxiety;
    private Integer stress;
    private Integer sleepTime;
    //DTO -> Entity = toEntity
    //Entity -> DTO = of
}
