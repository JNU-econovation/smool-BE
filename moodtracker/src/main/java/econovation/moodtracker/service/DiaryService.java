package econovation.moodtracker.service;

import econovation.moodtracker.domain.Diary;
import econovation.moodtracker.domain.Emotion;
import econovation.moodtracker.domain.dto.Request.DiaryCreateRequestDTO;
import econovation.moodtracker.domain.dto.Response.DiaryLogResponseDTO;
import econovation.moodtracker.domain.dto.Response.DiaryResponseDTO;
import econovation.moodtracker.domain.dto.Response.OneDiaryLogResponseDTO;
import econovation.moodtracker.repository.DiaryRepository;
import econovation.moodtracker.repository.EmotionRepository;
import econovation.moodtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final UserService userService;
    private final EmotionService emotionService;

    public Long join(DiaryCreateRequestDTO diaryCreateRequestDTO){
        LocalDate requestDTOLocalDate = diaryCreateRequestDTO.getLocalDate();
        LocalDateTime joinedDateTime = LocalDateTime.now()
                .withYear(requestDTOLocalDate.getYear())
                .withMonth(requestDTOLocalDate.getMonthValue())
                .withDayOfMonth(requestDTOLocalDate.getDayOfMonth());
        boolean existTodayEmotion = isExistTodayEmotion(diaryCreateRequestDTO);
        Diary diary;
        if (existTodayEmotion){
            diary = Diary.builder()
                    .time(joinedDateTime)
                    .content(diaryCreateRequestDTO.getContent())
                    .user(userService.findUser(diaryCreateRequestDTO.getUserPK()))
                    .emotion(emotionService.updateEmotion(diaryCreateRequestDTO))
                    .build();
        }else{
            diary = Diary.builder()
                    .time(joinedDateTime)
                    .content(diaryCreateRequestDTO.getContent())
                    .user(userService.findUser(diaryCreateRequestDTO.getUserPK()))
                    .emotion(emotionService.join(diaryCreateRequestDTO))
                    .build();
        }
        diaryRepository.save(diary);
        return diary.getId();
    }

    public void updateDiary(Long diaryPK, String content){
        Diary diary = findDiary(diaryPK);
        diary.update(content);
    }
    public Diary findDiary(Long diaryPK){
        return diaryRepository.findById(diaryPK)
                .orElseThrow(() -> new NullPointerException("일기가 없어용"));
    }
    public List<Diary> findAllDiaries(LocalDate localDate, Long userPK){

        LocalDateTime startTime = LocalDateTime.of(
                localDate.getYear(),
                localDate.getMonthValue(),
                localDate.getDayOfMonth(),
                0, 0, 0);;
        LocalDateTime endTime = startTime
                .withHour(23)
                .withMinute(59)
                .withSecond(59);

        return diaryRepository.findAllByTimeBetweenAndUserId(startTime, endTime, userPK);
    }
    public DiaryResponseDTO findOneDiary(Long diaryPK){
        Diary diary = findDiary(diaryPK);
        return DiaryResponseDTO.of(diary.getEmotion(), diary);
    }

    public DiaryLogResponseDTO findAllDiaryLog(LocalDate localDate){
        return DiaryLogResponseDTO.builder().build();
    }

    public void deleteDiary(Long diaryPK){
        Diary diary = findDiary(diaryPK);
        Emotion emotion = diary.getEmotion();
        Long emotionId = emotion.getId();
        List<Diary> diaries = diaryRepository.findAllByEmotionId(emotionId);
        if (diaries.size() == 1){
            emotionService.deleteEmotion(emotionId);
        }
        diaryRepository.delete(diary);
    }
    public boolean isExistTodayEmotion(DiaryCreateRequestDTO diaryCreateRequestDTO){
        List<Diary> getTodayDiaries = findAllDiaries(diaryCreateRequestDTO.getLocalDate(), diaryCreateRequestDTO.getUserPK());
        return !getTodayDiaries.isEmpty();
    }
}
