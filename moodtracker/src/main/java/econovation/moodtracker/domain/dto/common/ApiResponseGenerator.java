package econovation.moodtracker.domain.dto.common;

import econovation.moodtracker.domain.dto.common.ApiResponse.Result;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponseGenerator {
    public static <T>Result<T> success(T response, String message){
        return new Result<>(response, 200, message);
    }
    public static Result<?> error(String message){
        return new Result<>(null, 400, message);
    }
}
