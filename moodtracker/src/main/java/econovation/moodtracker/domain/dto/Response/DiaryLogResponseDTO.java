package econovation.moodtracker.domain.dto.Response;

import econovation.moodtracker.domain.Diary;
import econovation.moodtracker.domain.Emotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
//일기 로그(감정 1, 일기 여러 개)
public class DiaryLogResponseDTO {
    //일기가 있으면 보내기
    private Integer happiness;
    private Integer gloom;
    private Integer anxiety;
    private Integer stress;
    private Integer sleep;

    private List<OneDiaryLogDTO> oneDiaryLogResponseDTOs;

    public static DiaryLogResponseDTO of(Emotion emotion){
        List<Diary> diaries = emotion.getDiaries();
        List<OneDiaryLogDTO> oneDiaryLogResponseDTOs = new ArrayList<>();
        for (Diary diary : diaries) {
            oneDiaryLogResponseDTOs.add(OneDiaryLogDTO.of(diary));
        }
        return DiaryLogResponseDTO.builder()
                .happiness(emotion.getHappiness())
                .gloom(emotion.getGloom())
                .anxiety(emotion.getAnxiety())
                .stress(emotion.getStress())
                .sleep(emotion.getSleep())
                .oneDiaryLogResponseDTOs(oneDiaryLogResponseDTOs)
                .build();
    }
}
