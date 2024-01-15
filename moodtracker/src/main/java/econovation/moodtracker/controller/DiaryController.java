package econovation.moodtracker.controller;

import econovation.moodtracker.domain.dto.Request.DiaryCreateRequestDTO;
import econovation.moodtracker.domain.dto.Request.DiaryUpdateRequestDTO;
import econovation.moodtracker.domain.dto.Response.CalendarResponseDTO;
import econovation.moodtracker.domain.dto.Response.DiaryLogResponseDTO;
import econovation.moodtracker.domain.dto.Response.DiaryResponseDTO;
import econovation.moodtracker.domain.dto.common.ApiResponse;
import econovation.moodtracker.domain.dto.common.ApiResponseGenerator;
import econovation.moodtracker.domain.dto.common.CommonRequestDTO;
import econovation.moodtracker.domain.dto.common.CommonResponseDTO;
import econovation.moodtracker.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;
    @GetMapping("/calendar/{dates}")
    public ApiResponse.Result<CalendarResponseDTO> findCalendar(@RequestBody CommonRequestDTO commonRequestDTO, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("dates")LocalDate endDate){
        CalendarResponseDTO calendarResponseDTO = diaryService.findCalendar(endDate, commonRequestDTO.getUserPk());
        return ApiResponseGenerator.success(calendarResponseDTO, "캘린더 조회 성공");
    }
    @GetMapping("/calendar/date/{date}")
    public ApiResponse.Result<DiaryLogResponseDTO> findDiaryLog (@RequestBody CommonRequestDTO commonRequestDTO, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date")LocalDate date){
        DiaryLogResponseDTO diaryLogResponseDTO = diaryService.findAllDiaryLog(date, commonRequestDTO.getUserPk());
        return ApiResponseGenerator.success(diaryLogResponseDTO, "로그 조회 성공");
    }
    @PostMapping("/diaries")
    public ApiResponse.Result<?> createDiary(@RequestBody DiaryCreateRequestDTO diaryCreateRequestDTO){
        diaryService.join(diaryCreateRequestDTO);
        return ApiResponseGenerator.success(null, "일기 작성 완료");
    }

    @GetMapping("/diaries/{id}")
    public ApiResponse.Result<DiaryResponseDTO> findDiary(@PathVariable("id") Long diaryPK) {
        DiaryResponseDTO diaryResponseDTO = diaryService.findOneDiary(diaryPK);
        return ApiResponseGenerator.success(diaryResponseDTO, "일기 조회 성공");
    }

    @PutMapping("/diaries/{id}")
    public ResponseEntity<CommonResponseDTO> updateDiary(@PathVariable("id") Long diaryPK, @RequestBody DiaryUpdateRequestDTO diaryUpdateRequestDTO){
        diaryService.updateDiary(diaryUpdateRequestDTO);
        CommonResponseDTO commonResponseDTO = CommonResponseDTO.of("일기 수정 완료");
        return new ResponseEntity<>(commonResponseDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/diaries/{id}")
    public ResponseEntity<CommonResponseDTO> deleteDiary(@PathVariable("id") Long diaryPK){
        diaryService.deleteDiary(diaryPK);
        CommonResponseDTO commonResponseDTO = CommonResponseDTO.of("일기 삭제 완료");
        return new ResponseEntity<>(commonResponseDTO, HttpStatus.ACCEPTED);
    }

}
