package econovation.moodtracker.service;

import econovation.moodtracker.domain.Diary;
import econovation.moodtracker.domain.Emotion;
import econovation.moodtracker.domain.dto.Request.DiaryCreateRequestDTO;
import econovation.moodtracker.domain.dto.Request.DiaryUpdateRequestDTO;
import econovation.moodtracker.domain.dto.Response.CalendarResponseDTO;
import econovation.moodtracker.domain.dto.Response.DiaryLogResponseDTO;
import econovation.moodtracker.domain.dto.Response.DiaryResponseDTO;
import econovation.moodtracker.exception.DiaryNotFoundException;
import econovation.moodtracker.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
                    .user(userService.findUser(diaryCreateRequestDTO.getUserPk()))
                    .emotion(emotionService.updateEmotion(diaryCreateRequestDTO))
                    .build();
        }else{
            diary = Diary.builder()
                    .time(joinedDateTime)
                    .content(diaryCreateRequestDTO.getContent())
                    .user(userService.findUser(diaryCreateRequestDTO.getUserPk()))
                    .emotion(emotionService.join(diaryCreateRequestDTO))
                    .build();
        }
        diaryRepository.save(diary);
        return diary.getId();
    }

    public void updateDiary(DiaryUpdateRequestDTO diaryUpdateRequestDTO){
        Diary diary = findDiary(diaryUpdateRequestDTO.getDiaryPk());
        Emotion emotion = emotionService.findEmotion(diary.getEmotion().getId());

        diary.update(diaryUpdateRequestDTO.getContent());
        emotion.update(diaryUpdateRequestDTO.getHappiness(),
                diaryUpdateRequestDTO.getGloom(),
                diaryUpdateRequestDTO.getAnxiety(),
                diaryUpdateRequestDTO.getStress(),
                diaryUpdateRequestDTO.getSleep()
        );
    }
    public Diary findDiary(Long diaryPK){
        return diaryRepository.findById(diaryPK)
                .orElseThrow(DiaryNotFoundException::new);
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
    //특정 날자 로그
    public DiaryLogResponseDTO findAllDiaryLog(LocalDate localDate, Long userPK){
        List<Diary> diaries = findAllDiaries(localDate, userPK);
        if(diaries.isEmpty()){
            //보내줄 일기가 없으면 프론트로 null값 보내줌
            return null;
        }
        else {
            return DiaryLogResponseDTO.of(diaries.get(0).getEmotion());
        }
    }

    public CalendarResponseDTO findCalendar(LocalDate endDate, Long userPK){
        LocalDate startDate = endDate.withDayOfMonth(1);
        List<Boolean> existDates = IntStream.range(0, endDate.getDayOfMonth())
                .mapToObj(i -> !findAllDiaries(startDate.plusDays(i), userPK).isEmpty())
                .collect(Collectors.toList());
        return CalendarResponseDTO.of(endDate, existDates);
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
        List<Diary> getTodayDiaries = findAllDiaries(diaryCreateRequestDTO.getLocalDate(), diaryCreateRequestDTO.getUserPk());
        return !getTodayDiaries.isEmpty();
    }
}
