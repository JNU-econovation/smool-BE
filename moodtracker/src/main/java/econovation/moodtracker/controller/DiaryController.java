package econovation.moodtracker.controller;

import econovation.moodtracker.domain.dto.Response.CalendarResponseDTO;
import econovation.moodtracker.domain.dto.Response.DiaryLogResponseDTO;
import econovation.moodtracker.domain.dto.Response.DiaryResponseDTO;
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
    public ResponseEntity<CalendarResponseDTO> findCalendar(Long userPK, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("dates")LocalDate endDate){
        CalendarResponseDTO calendarResponseDTO = diaryService.findCalendar(endDate, userPK);
        return new ResponseEntity<>(calendarResponseDTO, HttpStatus.OK);
    }
    @GetMapping("/calendar/date/{date}")
    public ResponseEntity<DiaryLogResponseDTO> findDiaryLog (Long userPK, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date")LocalDate date){
        DiaryLogResponseDTO diaryLogResponseDTO = diaryService.findAllDiaryLog(date, userPK);
        return new ResponseEntity<>(diaryLogResponseDTO, HttpStatus.OK);
    }
    @GetMapping("/diaries/{id}")
    public ResponseEntity<DiaryResponseDTO> findDiary(@PathVariable("id") Long diaryPK) {
        DiaryResponseDTO diaryResponseDTO = diaryService.findOneDiary(diaryPK);
        return new ResponseEntity<>(diaryResponseDTO, HttpStatus.OK);
    }
}
