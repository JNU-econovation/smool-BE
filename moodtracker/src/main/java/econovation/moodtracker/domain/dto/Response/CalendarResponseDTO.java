package econovation.moodtracker.domain.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class CalendarResponseDTO {
    private List<CalendarDateDTO> existDates;

    public static CalendarResponseDTO of(LocalDate endDate, List<Boolean> tmp){
        List<CalendarDateDTO> existDates = new ArrayList<>();
        LocalDate startTime = endDate.withDayOfMonth(1);
        for(int i=0;i<tmp.size();i++){
            existDates.add(CalendarDateDTO.of(startTime.plusDays(i), tmp.get(i)));
        }
        return CalendarResponseDTO.builder()
                .existDates(existDates)
                .build();
    }
}
