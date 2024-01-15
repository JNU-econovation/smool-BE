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
public class UserResponseDTO {
    private Long userPk;
    public static UserResponseDTO of(Long userPk){
        return UserResponseDTO.builder()
                .userPk(userPk)
                .build();
    }
}
