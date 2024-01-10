package econovation.moodtracker.controller;

import econovation.moodtracker.domain.dto.Response.DiaryResponseDTO;
import econovation.moodtracker.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;
    @GetMapping("/diaries/{pk}")
    public ResponseEntity<DiaryResponseDTO> findDiary(@PathVariable("pk") Long diaryPK) {
        DiaryResponseDTO diaryResponseDTO = diaryService.findOneDiary(diaryPK);
        return new ResponseEntity<>(diaryResponseDTO, HttpStatus.OK);
    }
}
