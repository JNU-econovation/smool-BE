package econovation.moodtracker.domain.dto.Request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class UserCreateRequestDTO {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
