package econovation.moodtracker.domain.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

@Getter
@SuperBuilder
@AllArgsConstructor
public class UserLoginRequestDTO extends UserCreateRequestDTO{
}
