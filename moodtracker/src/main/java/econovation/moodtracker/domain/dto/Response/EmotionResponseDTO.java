package econovation.moodtracker.domain.emotion.DTO.Response;

import econovation.moodtracker.domain.emotion.Emotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class EmotionResponseDTO {
    private LocalTime localTime;
    private Integer happiness;
    private Integer sadness;
    private Integer anxiety;
    private Integer stress;
    private Integer sleepTime;
    //DTO -> Entity = toEntity
    //Entity -> DTO = of
    public static EmotionResponseDTO of(Emotion emotion){
        return EmotionResponseDTO.builder()
                .localTime(LocalTime.now())
                .happiness(emotion.getHappiness())
                .sadness(emotion.getSadness())
                .anxiety(emotion.getAnxiety())
                .stress(emotion.getStress())
                .sleepTime(emotion.getSleepTime())
                .build();
    }
}