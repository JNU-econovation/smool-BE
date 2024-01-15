package econovation.moodtracker.domain.dto.common;

import econovation.moodtracker.domain.dto.common.ApiResponse.Result;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ApiResponseGenerator {
    public static <T>Result<T> success(T response, String message){
        return new Result<>(response, 400, message);
    }
    public static Result<?> error(String message){
        return new Result<>(null, 200, message);
    }
}
