package econovation.moodtracker.domain.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class EmotionResponseDTO {
    List<EmotionDateDTO> happiness;
    List<EmotionDateDTO> gloom;
    List<EmotionDateDTO> anxiety;
    List<EmotionDateDTO> stress;
    List<EmotionDateDTO> sleep;

    public static EmotionResponseDTO of(List<EmotionDateDTO> happiness, List<EmotionDateDTO> gloom,List<EmotionDateDTO> anxiety,List<EmotionDateDTO> stress,List<EmotionDateDTO> sleep){
        return EmotionResponseDTO.builder()
                .happiness(happiness)
                .gloom(gloom)
                .anxiety(anxiety)
                .stress(stress)
                .sleep(sleep)
                .build();
    }
}
