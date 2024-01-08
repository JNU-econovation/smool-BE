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
        return emotionRepository.findById(diaryUpdateRequestDTO.getEmotionPK())
                .orElseThrow(() -> new NullPointerException("감정이 없어용"));
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

        List<Diary> diaries = diaryRepository.findAllByTimeBetweenAndUserId(startTime, endTime, diaryCreateRequestDTO.getUserPK());
        Emotion emotion;
//        if (diaries.isEmpty()){
//            emotion = this.join(diaryCreateRequestDTO);
//        }
//        else {
//            Long emotionPK = diaries.get(0).getEmotion().getId();
//            emotion = emotionRepository.findById(emotionPK).get();
//            emotion.update(diaryCreateRequestDTO.getHappiness(), diaryCreateRequestDTO.getGloom(),
//                    diaryCreateRequestDTO.getAnxiety(), diaryCreateRequestDTO.getStress(),
//                    diaryCreateRequestDTO.getSleep());
            emotion = diaries.get(0).getEmotion();
            emotion.update(diaryCreateRequestDTO.getHappiness(), diaryCreateRequestDTO.getGloom(),
                    diaryCreateRequestDTO.getAnxiety(), diaryCreateRequestDTO.getStress(),
                    diaryCreateRequestDTO.getSleep());
//        }
        return emotion;
    }

    public Emotion getEmotion(Long emotionPK){
        return emotionRepository.findById(emotionPK)
                .orElseThrow(() -> new NullPointerException("감정이 없어용"));
    }
}
