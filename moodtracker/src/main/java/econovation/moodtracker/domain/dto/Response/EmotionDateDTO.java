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
public class EmotionDateDTO {
    private LocalDate localDate;
    private Integer value;

    public static EmotionDateDTO of(LocalDate localDate, Integer value){
        return EmotionDateDTO.builder()
                .localDate(localDate)
                .value(value)
                .build();
    }
}