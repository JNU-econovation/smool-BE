package econovation.moodtracker.controller;

import econovation.moodtracker.domain.dto.Request.DiaryCreateRequestDTO;
import econovation.moodtracker.domain.dto.Request.DiaryUpdateRequestDTO;
import econovation.moodtracker.domain.dto.Response.CalendarResponseDTO;
import econovation.moodtracker.domain.dto.Response.DiaryLogResponseDTO;
import econovation.moodtracker.domain.dto.Response.DiaryResponseDTO;
import econovation.moodtracker.domain.dto.common.ApiResponse;
import econovation.moodtracker.domain.dto.common.ApiResponseGenerator;
import econovation.moodtracker.domain.dto.common.CommonRequestDTO;
import econovation.moodtracker.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;
    @GetMapping("/calendar/{userPk}/{dates}")
    public ApiResponse.Result<CalendarResponseDTO> findCalendar(@PathVariable("userPk") Long userPk, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("dates")LocalDate endDate){
        CalendarResponseDTO calendarResponseDTO = diaryService.findCalendar(endDate, userPk);
        return ApiResponseGenerator.success(calendarResponseDTO, "캘린더 조회 성공");
    }
    @GetMapping("/calendar/{userPk}/date/{date}")
    public ApiResponse.Result<DiaryLogResponseDTO> findDiaryLog (@PathVariable("userPk") Long userPk, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date")LocalDate date){
        DiaryLogResponseDTO diaryLogResponseDTO = diaryService.findAllDiaryLog(date, userPk);
        return ApiResponseGenerator.success(diaryLogResponseDTO, "로그 조회 성공");
    }
    @PostMapping("/diaries")
    public ApiResponse.Result<?> createDiary(@Validated @RequestBody DiaryCreateRequestDTO diaryCreateRequestDTO){
        diaryService.join(diaryCreateRequestDTO);
        return ApiResponseGenerator.success(null, "일기 작성 완료");
    }

    @GetMapping("/diaries/{id}")
    public ApiResponse.Result<DiaryResponseDTO> findDiary(@PathVariable("id") Long diaryPK) {
        DiaryResponseDTO diaryResponseDTO = diaryService.findOneDiary(diaryPK);
        return ApiResponseGenerator.success(diaryResponseDTO, "일기 조회 성공");
    }

    @PutMapping("/diaries/{id}")
    public ApiResponse.Result<?> updateDiary(@PathVariable("id") Long diaryPK, @Validated @RequestBody DiaryUpdateRequestDTO diaryUpdateRequestDTO){
        diaryService.updateDiary(diaryUpdateRequestDTO);
        return ApiResponseGenerator.success(null, "일기 수정 완료");
    }

    @DeleteMapping("/diaries/{id}")
    public ApiResponse.Result<?> deleteDiary(@PathVariable("id") Long diaryPK){
        diaryService.deleteDiary(diaryPK);
        return ApiResponseGenerator.success(null, "일기 삭제 완료");
    }

}
