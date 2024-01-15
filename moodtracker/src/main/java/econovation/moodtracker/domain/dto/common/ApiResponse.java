package econovation.moodtracker.domain.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@Getter
public class ApiResponse<T> extends ResponseEntity<T> {

    public ApiResponse(T body, HttpStatusCode status) {
        super(body, status);
    }

    @Getter
    @AllArgsConstructor
    public static class Result<T>{
        @JsonProperty("data")
        private T response;
        private int status;
        private String message;
    }
}
