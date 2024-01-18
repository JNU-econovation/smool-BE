package econovation.moodtracker.domain.dto.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class DiaryCreateRequestDTO {
    // 날짜
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;
    // 일기 domain
    private Long userPk;
    private String content;

    // 감정 domain
    private Integer happiness;
    @Min(0) @Max(10)
    private Integer gloom;
    @Min(0) @Max(10)
    private Integer anxiety;
    @Min(0) @Max(10)
    private Integer stress;
    @Min(0) @Max(10)
    private Integer sleep;
}
