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

    public void updateEmotion(DiaryUpdateRequestDTO diaryUpdateRequestDTO){
        Emotion Emotion = emotionRepository.findById(diaryUpdateRequestDTO.getEmotionPK())
                .orElseThrow(() -> new NullPointerException("감정이 없어용"));
    }
}
