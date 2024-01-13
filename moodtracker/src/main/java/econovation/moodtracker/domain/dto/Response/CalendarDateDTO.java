package econovation.moodtracker.domain.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class CalendarDateDTO {
    private boolean isExist;

    public static CalendarDateDTO of(boolean isExist){
        return CalendarDateDTO.builder()
                .isExist(isExist)
                .build();
    }
}
