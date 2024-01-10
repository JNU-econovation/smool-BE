package econovation.moodtracker.service;

import econovation.moodtracker.domain.Diary;
import econovation.moodtracker.domain.Emotion;
import econovation.moodtracker.domain.dto.Request.DiaryCreateRequestDTO;
import econovation.moodtracker.domain.dto.Response.DiaryLogResponseDTO;
import econovation.moodtracker.domain.dto.Response.DiaryResponseDTO;
import econovation.moodtracker.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        Diary diary = findDiary(diaryPK)
                .orElseThrow(() -> new NullPointerException("일기가 없어용"));
        diary.update(content);
    }
    public Optional<Diary> findDiary(Long diaryPK){
        return diaryRepository.findById(diaryPK);
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

        return diaryRepository.findAllByTimeBetweenAndUserId(startTime, endTime, userPK)
                .orElseThrow(() -> new NullPointerException("이 에러는 불가능한 에러"));
    }
    public DiaryResponseDTO findOneDiary(Long diaryPK){
        Diary diary = findDiary(diaryPK)
                .orElseThrow(() -> new NullPointerException("일기가 없어용"));
        return DiaryResponseDTO.of(diary.getEmotion(), diary);
    }

    public DiaryLogResponseDTO findAllDiaryLog(LocalDate localDate, Long userPK){
        //일기가 없을 때도 있어서 Optional로 받아야함
        List<Diary> diaries = findAllDiaries(localDate, userPK);
        return DiaryLogResponseDTO.of(diaries.get(0).getEmotion());
    }

    public void deleteDiary(Long diaryPK){
        Diary diary = findDiary(diaryPK)
                .orElseThrow(() -> new NullPointerException("일기가 없어용"));
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
