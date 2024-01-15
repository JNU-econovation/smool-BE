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
public class OneDiaryLogDTO {
    private Long userPk;
    private String content;
    private LocalTime localTime;

    public static OneDiaryLogDTO of(Diary diary){
        return OneDiaryLogDTO.builder()
                .userPk(diary.getId())
                .content(diary.getContent())
                .localTime(diary.getTime().toLocalTime())
                .build();
    }
}
