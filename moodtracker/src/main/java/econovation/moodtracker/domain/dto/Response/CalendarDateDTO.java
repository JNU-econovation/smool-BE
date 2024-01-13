package econovation.moodtracker.domain.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class CalendarDateDTO {
    private LocalDate localDate;
    private boolean isExist;

    public static CalendarDateDTO of(LocalDate localDate, boolean isExist){
        return CalendarDateDTO.builder()
                .localDate(localDate)
                .isExist(isExist)
                .build();
    }
}
