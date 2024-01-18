package econovation.moodtracker.exception;

import econovation.moodtracker.domain.dto.common.ApiResponse;
import econovation.moodtracker.domain.dto.common.ApiResponseGenerator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode httpStatusCode, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(ApiResponseGenerator.error(errorMessage), httpStatusCode);
    }
    @ExceptionHandler(DiaryNotFoundException.class)
    public ApiResponse.Result<?> handleDiaryNotFoundException(Exception e){
        return ApiResponseGenerator.error(e.getMessage());
    }
    @ExceptionHandler(EmotionNotFoundException.class)
    public ApiResponse.Result<?> handleEmotionNotFoundException(Exception e){
        return ApiResponseGenerator.error(e.getMessage());
    }
    @ExceptionHandler(PasswordNotCorrectException.class)
    public ApiResponse.Result<?> handlePasswordNotCorrectException(Exception e){
        return ApiResponseGenerator.error(e.getMessage());
    }
    @ExceptionHandler(SameUserIdException.class)
    public ApiResponse.Result<?> handleSameUserIdException(Exception e){
        return ApiResponseGenerator.error(e.getMessage());
    }

    @ExceptionHandler(StatisticsEmotionNotFoundException.class)
    public ApiResponse.Result<?> handleStatisticsEmotionNotFoundException(Exception e){
        return ApiResponseGenerator.error(e.getMessage());
    }

    @ExceptionHandler(UserIdNotFountException.class)
    public ApiResponse.Result<?> handleUserIdNotFountExceptionException(Exception e){
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
