package econovation.moodtracker.controller;

import econovation.moodtracker.domain.dto.Response.EmotionResponseDTO;
import econovation.moodtracker.domain.dto.common.ApiResponse;
import econovation.moodtracker.domain.dto.common.ApiResponseGenerator;
import econovation.moodtracker.domain.dto.common.CommonRequestDTO;
import econovation.moodtracker.service.EmotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class EmotionController {
    private final EmotionService emotionService;

    @GetMapping("/emotion/{userPk}/statistics/{dates}")
    public ApiResponse.Result<EmotionResponseDTO> findCalendar(@PathVariable ("userPk") Long userPk, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("dates") LocalDate endDate){
        EmotionResponseDTO emotionResponseDTO = emotionService.findStatisticsEmotions(endDate,userPk);
        return ApiResponseGenerator.success(emotionResponseDTO, "통계 조회 성공");
    }
}
