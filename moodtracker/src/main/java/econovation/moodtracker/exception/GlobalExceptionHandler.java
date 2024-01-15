package econovation.moodtracker.exception;

import econovation.moodtracker.domain.dto.common.ApiResponse;
import econovation.moodtracker.domain.dto.common.ApiResponseGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DiaryNotFoundException.class)
    public ApiResponse.Result<?> handleDiaryNotFoundExceptionException(Exception e){
        return ApiResponseGenerator.error(e.getMessage());
    }
    @ExceptionHandler(EmotionNotFoundException.class)
    public ApiResponse.Result<?> handleEmotionNotFoundException(Exception e){
        return ApiResponseGenerator.error(e.getMessage());
    }
    @ExceptionHandler(SameUserIdException.class)
    public ApiResponse.Result<?> handleSameUserIdException(Exception e){
        return ApiResponseGenerator.error(e.getMessage());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ApiResponse.Result<?> handleUserNotFoundException(Exception e){
        return ApiResponseGenerator.error(e.getMessage());
    }
    @ExceptionHandler(UserPasswordNotCorrect.class)
    public ApiResponse.Result<?> handleUserPasswordNotCorrectException(Exception e){
        return ApiResponseGenerator.error(e.getMessage());
    }
}
