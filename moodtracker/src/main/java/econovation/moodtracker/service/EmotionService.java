package econovation.moodtracker.service;


import econovation.moodtracker.domain.dto.Request.DiaryCreateRequestDTO;
import econovation.moodtracker.domain.Emotion;
import econovation.moodtracker.domain.dto.Request.DiaryUpdateRequestDTO;
import econovation.moodtracker.repository.EmotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class EmotionService {

    private final EmotionRepository emotionRepository;

    public void join(DiaryCreateRequestDTO diaryCreateRequestDTO){

        Emotion emotion = Emotion.builder()
                .happiness(diaryCreateRequestDTO.getHappiness())
                .sadness(diaryCreateRequestDTO.getSadness())
                .anxiety(diaryCreateRequestDTO.getAnxiety())
                .stress(diaryCreateRequestDTO.getStress())
                .sleepTime(diaryCreateRequestDTO.getSleepTime())
                .diaries(new ArrayList<>())
                .build();
        emotionRepository.save(emotion);
    }

    public void updateEmotion(DiaryUpdateRequestDTO diaryUpdateRequestDTO){
        Emotion Emotion = emotionRepository.findById(diaryUpdateRequestDTO.getEmotionPK())
                .orElseThrow(() -> new NullPointerException("감정이 없어용"));
    }
}
