package econovation.moodtracker.domain.dto.Response;

import econovation.moodtracker.domain.Diary;
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
//일기 로그 조회할 때 일기 하나
public class OneDiaryLogResponseDTO {
    private String content;
    private LocalTime localTime;

    public static OneDiaryLogResponseDTO of(Diary diary){
        return OneDiaryLogResponseDTO.builder()
                .content(diary.getContent())
                .localTime(diary.getTime().toLocalTime())
                .build();
    }
}
