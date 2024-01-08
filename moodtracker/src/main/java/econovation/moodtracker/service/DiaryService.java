package econovation.moodtracker.service;

import econovation.moodtracker.domain.Diary;
import econovation.moodtracker.domain.Emotion;
import econovation.moodtracker.domain.dto.Request.DiaryCreateRequestDTO;
import econovation.moodtracker.repository.DiaryRepository;
import econovation.moodtracker.repository.EmotionRepository;
import econovation.moodtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;
    private final EmotionService emotionService;

    public Long join(DiaryCreateRequestDTO diaryCreateRequestDTO){
        LocalDate localDate = diaryCreateRequestDTO.getLocalDate();
        LocalDateTime joinedDateTime = LocalDateTime.now()
                .withYear(localDate.getYear())
                .withMonth(localDate.getMonthValue())
                .withDayOfMonth(localDate.getDayOfMonth());

        Diary diary = Diary.builder()
                .time(joinedDateTime)
                .content(diaryCreateRequestDTO.getContent())
                .user(userRepository.findById(diaryCreateRequestDTO.getUserPK()).get())
                .emotion(emotionService.join(diaryCreateRequestDTO))
                .build();
        diaryRepository.save(diary);
        return diary.getId();
    }

    public void updateDiary(Long diaryPK, String content){
        Diary diary = diaryRepository.findById(diaryPK)
                .orElseThrow(() -> new NullPointerException("일기가 없어용"));
        diary.update(content);
    }
}
