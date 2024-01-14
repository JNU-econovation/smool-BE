package econovation.moodtracker.domain.dto.common;

import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class CommonRequestDTO {
    private Long userPk;
}
