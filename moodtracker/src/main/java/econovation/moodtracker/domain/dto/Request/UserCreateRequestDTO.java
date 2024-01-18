package econovation.moodtracker.domain.dto.Request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class UserCreateRequestDTO {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.[a-zA-Z])(?=.[0-9])(?=.[#?!@$%^&-]).{8,}$"
            , message = "비밀번호는 8자 이상, 숫자, 영문 대/소문자, 특수문자를 조합해주세요.")
    private String password;
    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    private String checkPassword;
}
