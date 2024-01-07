package econovation.moodtracker.domain.dto.Response;

import econovation.moodtracker.domain.diary.Diary;
import econovation.moodtracker.domain.emotion.Emotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class DiaryResponseDTO {
//    LocalDateTime localDateTime = LocalDateTime.now();
//    LocalTime localTime = localDateTime.toLocalTime();

//DTO -> Entity = toEntity
//Entity -> DTO = of
    // 사용자 domain
    private Long userPK;
    // 일기 domain
    private String content;

    // 감정 domain
    private Integer happiness;
    private Integer sadness;
    private Integer anxiety;
    private Integer stress;
    private Integer sleepTime;

    public static DiaryResponseDTO of(Diary diary){
        return DiaryResponseDTO.builder()
                .content(diary.getContent())
                .build();
    }

    public static DiaryResponseDTO of(Emotion emotion){
        return DiaryResponseDTO.builder()
                .happiness(emotion.getHappiness())
                .sadness(emotion.getSadness())
                .anxiety(emotion.getAnxiety())
                .stress(emotion.getStress())
                .sleepTime(emotion.getSleepTime())
                .build();
    }
}
