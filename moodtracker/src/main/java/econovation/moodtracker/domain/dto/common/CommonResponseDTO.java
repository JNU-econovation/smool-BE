package econovation.moodtracker.domain.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class CommonResponseDTO {
    private String message;

    public static CommonResponseDTO of(String message){
        return CommonResponseDTO.builder()
                .message(message)
                .build();
    }
}
