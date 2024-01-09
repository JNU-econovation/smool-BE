package econovation.moodtracker.domain.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class DiaryLogResponseDTO {
    //일기가 있으면 보내기
    private Integer happiness;
    private Integer gloom;
    private Integer anxiety;
    private Integer stress;
    private Integer sleep;

    private List<OneDiaryLogResponseDTO> oneDiaryLogResponseDTOs;
}
