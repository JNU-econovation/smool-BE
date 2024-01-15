package econovation.moodtracker.service;


import econovation.moodtracker.domain.Diary;
import econovation.moodtracker.domain.dto.Request.DiaryCreateRequestDTO;
import econovation.moodtracker.domain.Emotion;
import econovation.moodtracker.domain.dto.Request.DiaryUpdateRequestDTO;
import econovation.moodtracker.repository.DiaryRepository;
import econovation.moodtracker.repository.EmotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmotionService {

    private final EmotionRepository emotionRepository;
    private final DiaryRepository diaryRepository;
    public Emotion join(DiaryCreateRequestDTO diaryCreateRequestDTO){

        Emotion emotion = Emotion.builder()
                .happiness(diaryCreateRequestDTO.getHappiness())
                .gloom(diaryCreateRequestDTO.getGloom())
                .anxiety(diaryCreateRequestDTO.getAnxiety())
                .stress(diaryCreateRequestDTO.getStress())
                .sleep(diaryCreateRequestDTO.getSleep())
                .diaries(new ArrayList<>())
                .build();
        return emotionRepository.save(emotion);
    }

    public Emotion updateEmotion(DiaryUpdateRequestDTO diaryUpdateRequestDTO){
        Emotion emotion = findEmotion(diaryUpdateRequestDTO.getEmotionPk());
        emotion.update(diaryUpdateRequestDTO.getHappiness(),
                diaryUpdateRequestDTO.getGloom(),
                diaryUpdateRequestDTO.getAnxiety(),
                diaryUpdateRequestDTO.getStress(),
                diaryUpdateRequestDTO.getSleep());
        return emotion;
    }

    public Emotion updateEmotion(DiaryCreateRequestDTO diaryCreateRequestDTO){
        LocalDate localDate = diaryCreateRequestDTO.getLocalDate();
        LocalDateTime startTime = LocalDateTime.now()
                .withYear(localDate.getYear())
                .withMonth(localDate.getMonthValue())
                .withDayOfMonth(localDate.getDayOfMonth())
                .withHour(0)
                .withMinute(0);
        LocalDateTime endTime = startTime
                .withHour(23)
                .withMinute(59)
                .withSecond(59);

        List<Diary> diaries = diaryRepository.findAllByTimeBetweenAndUserId(startTime, endTime, diaryCreateRequestDTO.getUserPk());
        if (diaries.isEmpty()){
            throw new NullPointerException("일기가 없어용");
        }
        Emotion emotion = diaries.get(0).getEmotion();
        emotion.update(diaryCreateRequestDTO.getHappiness(), diaryCreateRequestDTO.getGloom(),
                       diaryCreateRequestDTO.getAnxiety(), diaryCreateRequestDTO.getStress(),
                       diaryCreateRequestDTO.getSleep());

        return emotion;
    }

    public Emotion findEmotion(Long emotionPK){
        return emotionRepository.findById(emotionPK)
                .orElseThrow(() -> new NullPointerException("감정이 없어용"));
    }

    public void deleteEmotion(Long emotionPK){
        emotionRepository.delete(findEmotion(emotionPK));
    }
}
