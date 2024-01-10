package econovation.moodtracker.domain.dto.Response;

import econovation.moodtracker.domain.Diary;
import econovation.moodtracker.domain.Emotion;
import econovation.moodtracker.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
//일기 조회 화면
public class DiaryResponseDTO {
//    LocalDateTime localDateTime = LocalDateTime.now();
//    LocalTime localTime = localDateTime.toLocalTime();

//DTO -> Entity = toEntity
//Entity -> DTO = of
    // 감정 domain
    private Integer happiness;
    private Integer gloom;
    private Integer anxiety;
    private Integer stress;
    private Integer sleep;
    // 일기 domain
    private String content;

    public static DiaryResponseDTO of(Emotion emotion, Diary diary) {
        return DiaryResponseDTO.builder()
                .happiness(emotion.getHappiness())
                .gloom(emotion.getGloom())
                .anxiety(emotion.getAnxiety())
                .stress(emotion.getStress())
                .sleep(emotion.getSleep())

                .content(diary.getContent())
                .build();
    }
}
